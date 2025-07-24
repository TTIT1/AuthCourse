package com.example.springjpa.repositorys;

import com.example.springjpa.models.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LectureRepository extends JpaRepository<Lecture,Integer> {


}
