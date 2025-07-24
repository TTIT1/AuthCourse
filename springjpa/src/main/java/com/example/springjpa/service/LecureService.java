package com.example.springjpa.service;

import com.example.springjpa.dto.LectureDTO;
import com.example.springjpa.dto.ResourceDTO;
import com.example.springjpa.models.Lecture;
import com.example.springjpa.models.Resource;
import com.example.springjpa.models.Section;
import com.example.springjpa.repositorys.LectureRepository;
import com.example.springjpa.repositorys.SectionRepository;
import org.hibernate.query.results.Builders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LecureService   {
       @Autowired
       SectionRepository sectionRepository;
       @Autowired
       LectureRepository lectureRepository;
       public ResourceDTO toModelRes(Resource resource) {
              ResourceDTO  resourceDTO = new ResourceDTO();
              resourceDTO.setId(resource.getId());
              resourceDTO.setName(resource.getName());
              resourceDTO.setUrl(resource.getUrl());
              resourceDTO.setLectureid(resource.getLecture().getId());
              return (resourceDTO);

       }
       public LectureDTO toModelLecture(Lecture lecture){
              LectureDTO lectureDTO = new LectureDTO();
              lectureDTO.setId(lecture.getId());
              lectureDTO.setName(lecture.getName());
              lectureDTO.setSectionId(lecture.getSection().getId());


            return  lectureDTO;

       }
       public LectureDTO addLecture (LectureDTO lectureDTO){
              Section section = sectionRepository.findById(lectureDTO.getSectionId()).orElseThrow(()->new RuntimeException("no find"));
              if (section!=null){

                     Lecture lecture = new Lecture();
                     lecture.setName(lectureDTO.getName());
                     lecture.setSection(section);

                     lectureRepository.save(lecture);
                     return  lectureDTO;
              }else {
                     throw new RuntimeException("no find ");
              }

       }
       public List<LectureDTO> toModelLectureList(){
              return  lectureRepository.findAll().stream().map(this::toModelLecture).collect(Collectors.toList());
       }


}
