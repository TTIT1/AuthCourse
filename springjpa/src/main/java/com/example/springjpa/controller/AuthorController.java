package com.example.springjpa.controller;

import com.example.springjpa.dto.AuthorDTO;
import com.example.springjpa.dto.LectureDTO;
import com.example.springjpa.dto.ResourceDTO;
import com.example.springjpa.dto.SectionDTO;
import com.example.springjpa.repositorys.SectionRepository;
import com.example.springjpa.service.AuthorService;
import com.example.springjpa.service.LecureService;
import com.example.springjpa.service.ResourceService;
import com.example.springjpa.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class AuthorController {
    @Autowired
    AuthorService authorService;
    @GetMapping()
    public List<AuthorDTO> getAll() {
        return authorService.getAllAuthor();
    }

    @PostMapping("/all/author-end-course")
    public ResponseEntity<?> all(@RequestBody AuthorDTO authorDTO) {
        try {
            if (authorDTO.getCourses() != null) {
                authorService.AllNew(authorDTO);
                return ResponseEntity.status(HttpStatus.OK).body("thanh công");
            }else {
                authorService.AllNew(authorDTO);
                return ResponseEntity.status(HttpStatus.OK).body("thanh công no âata course");
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody AuthorDTO authorDTO ){
        try {
            AuthorDTO authorDTO1 = authorService.update(authorDTO);
            return  ResponseEntity.status(HttpStatus.OK).body("Thanh công");
        } catch (Exception e) {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        boolean isDeleted = authorService.delete(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Không tìm thấy tác giả với ID: " + id);
        }
    }

}