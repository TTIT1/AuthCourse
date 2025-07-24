package com.example.springjpa.controller;

import com.example.springjpa.config.notification;
import com.example.springjpa.dto.CourseDTO;
import com.example.springjpa.repositorys.CourseRepository;
import com.example.springjpa.repositorys.LectureRepository;
import com.example.springjpa.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v3")
public class CourseController {
    @Autowired
    CourseService  courseService;

    @GetMapping
    public CourseDTO patchCourse(@RequestBody CourseDTO courseDTO){
        try{

             return  courseService.addCourse(courseDTO);
        }catch(Exception e){
           return new CourseDTO();
        }
    }
    @PutMapping("/update")
    public  ResponseEntity<?> update ( @RequestBody CourseDTO courseDTO){
        try {
           CourseDTO courseDTO1 =    courseService.updateCourse(courseDTO);
            return ResponseEntity.status(HttpStatus.OK).body(courseDTO1);
        } catch (Exception e) {
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    @DeleteMapping("/delete/{id}")
    public  ResponseEntity<?> deleteCourse(@PathVariable Integer id){
        try {
            courseService.deleteCourse(id);
            return ResponseEntity.status(HttpStatus.OK).body("Course deleted successfully");
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
