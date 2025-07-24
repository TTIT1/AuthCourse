package com.example.springjpa.repositorys;

import com.example.springjpa.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Integer> {
    Optional<Author> findByEmail(String email);


}
