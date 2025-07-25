package com.example.springjpa.repositorys;

import com.example.springjpa.models.Course;
import com.example.springjpa.models.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static ch.qos.logback.core.joran.spi.ConsoleTarget.findByName;

@Repository
public interface SectionRepository extends JpaRepository<Section,Integer> {
       Optional<Section>findById( Integer integer);
}
