package com.example.springjpa.service;

import com.example.springjpa.config.notification;
import com.example.springjpa.dto.AuthorDTO;
import com.example.springjpa.dto.CourseDTO;
import com.example.springjpa.models.Author;
import com.example.springjpa.models.Course;
import com.example.springjpa.repositorys.AuthorRepository;
import com.example.springjpa.repositorys.CourseRepository;
import com.sun.jdi.request.DuplicateRequestException;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthorService {
    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    CourseRepository courseRepository;

    private AuthorDTO toModelAuthorDTO(Author author) {
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setAge(author.getAge());
        authorDTO.setId(author.getId());
        authorDTO.setEmail(author.getEmail());
        authorDTO.setLastName(author.getLastName());
        authorDTO.setFirstName(author.getFirstName());
        List<CourseDTO> courseDTOS = author.getCourses().stream().map(Course ->
                {
                    CourseDTO courseDTO = new CourseDTO();
                    courseDTO.setId(Course.getId());
                    courseDTO.setTitle(Course.getTitle());
                    courseDTO.setDescription(Course.getDescription());
                    return courseDTO;
                }
        ).collect(Collectors.toList());
        authorDTO.setCourses(courseDTOS);
        return authorDTO;
    }

    // get all
    public List<AuthorDTO> getAllAuthor() {
        return authorRepository.findAll().stream().map(this::toModelAuthorDTO).collect(Collectors.toList());
    }

    public AuthorDTO AllNew(AuthorDTO authorDTO) {
        //tìm author
        try {
            Author author = authorRepository.findByEmail(authorDTO.getEmail()).orElse(null);
            if (author == null) {
                Author author1 = new Author();
                author1.setAge(authorDTO.getAge());
                author1.setEmail(authorDTO.getEmail());
                author1.setLastName(authorDTO.getLastName());
                author1.setFirstName(authorDTO.getFirstName());
                authorRepository.save(author1);
            }
            if (authorDTO.getCourses() != null) {
                for (CourseDTO courseDTO : authorDTO.getCourses()) {
                    Course course = courseRepository.findByTitle(courseDTO.getTitle()).orElse(null);
                    if (course == null) {
                        Course course1 = new Course();
                        course1.setTitle(courseDTO.getTitle());
                        course1.setDescription(courseDTO.getDescription());
                        List<Author> authors = new ArrayList<>();
                        authors.add(author);
                        course1.setAuthors(authors);
                        courseRepository.save(course1);
                    }
                }
            }
            return toModelAuthorDTO(author);
        } catch (Exception e) {
            throw new notification("khong tim thấy");
        }
    }

    public AuthorDTO update(AuthorDTO authorDTO) {
        Author author = authorRepository.findById(authorDTO.getId())
                .orElseThrow(() -> new RuntimeException("I don't find data for id: " + authorDTO.getId()));
        try {
            author.setFirstName(authorDTO.getFirstName());
            author.setAge(authorDTO.getAge());
            author.setEmail(authorDTO.getEmail());
            author.setLastName(authorDTO.getLastName());
            authorRepository.save(author);
            return authorDTO;
        } catch (Exception e) {
            throw new notification("Thiêu thong tin // sai lêch information");
        }

    }

    public boolean delete(Integer id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy tác giả với ID: " + id));
        author.getCourses().clear();
        authorRepository.save(author);
        authorRepository.deleteById(id);

        return true;
    }
}




