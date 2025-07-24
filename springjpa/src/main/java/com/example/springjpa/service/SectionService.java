package com.example.springjpa.service;

import com.example.springjpa.dto.SectionDTO;
import com.example.springjpa.models.Course;
import com.example.springjpa.models.Section;
import com.example.springjpa.repositorys.CourseRepository;
import com.example.springjpa.repositorys.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SectionService {
    private SectionDTO toModel(Section section){
        SectionDTO sectionDTO = new SectionDTO();
        sectionDTO.setId(section.getId());
        sectionDTO.setName(section.getName());
        sectionDTO.setOrder(section.getOrder());
      return  sectionDTO;
    }
    @Autowired
    SectionRepository sectionRepository;
    @Autowired
    CourseRepository courseRepository;
    public SectionDTO add (SectionDTO  sectionDTO) {

        Course course = courseRepository.findById(sectionDTO.getCourseId())
                .orElseThrow(()->new RuntimeException("no find course"));
        Section section = new Section();

        section.setName(sectionDTO.getName());
        section.setOrder(sectionDTO.getOrder());
        section.setCourse(course); // Gán course vào section

        sectionRepository.save(section);

        return sectionDTO;
    }
    public List<SectionDTO> getAllSection(){
        return  sectionRepository.findAll().stream()
                .map(this::toModel)
                .collect(Collectors.toUnmodifiableList());
    }
}
