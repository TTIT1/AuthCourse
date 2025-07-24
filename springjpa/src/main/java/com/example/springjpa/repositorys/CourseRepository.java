package com.example.springjpa.repositorys;

import com.example.springjpa.models.Course;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CourseRepository  extends JpaRepository<Course, Integer> {
    Optional<Course> findByTitle(String title);

}
