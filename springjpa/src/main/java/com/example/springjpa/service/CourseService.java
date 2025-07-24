package com.example.springjpa.service;

import com.example.springjpa.dto.AuthorDTO;
import com.example.springjpa.dto.CourseDTO;
import com.example.springjpa.dto.SectionDTO;
import com.example.springjpa.models.Author;
import com.example.springjpa.models.Course;
import com.example.springjpa.repositorys.AuthorRepository;
import com.example.springjpa.repositorys.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseService {
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    AuthorRepository authorRepository;
  public CourseDTO addCourse(CourseDTO courseDTO){
         Optional<Author> author = authorRepository.findById(courseDTO.getAuthorId());
    if(author.isPresent()){
        Course course = new Course();
        course.setDescription(courseDTO.getDescription());
        course.setTitle(courseDTO.getTitle());
        List<Author> authors = new ArrayList<>();
        authors.add(author.get());
        course.setAuthors(authors);
        courseRepository.save(course);
    }


      return courseDTO;
  }
    public CourseDTO updateCourse(CourseDTO courseDTO) {
        Optional<Course> course = courseRepository.findById(courseDTO.getId());
        if (course.isPresent()) {
            Course course1 = course.get();
            course1.setDescription(courseDTO.getDescription());
            course1.setTitle(courseDTO.getTitle());
            courseRepository.save(course1);
        }
        return  courseDTO;
    }



}
