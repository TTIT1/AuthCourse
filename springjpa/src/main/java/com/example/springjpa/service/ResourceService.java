package com.example.springjpa.service;

import com.example.springjpa.dto.ResourceDTO;
import com.example.springjpa.models.Lecture;
import com.example.springjpa.models.Resource;
import com.example.springjpa.repositorys.LectureRepository;
import com.example.springjpa.repositorys.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResourceService {
    @Autowired
    ResourceRepository resourceService;
    @Autowired
    LectureRepository lectureRepository;
public ResourceDTO add(ResourceDTO resourceDTO){
    Lecture lecture = lectureRepository.findById(resourceDTO.getLectureid())
            .orElseThrow(()->new RuntimeException("null"));
    if(lecture!=null){
        Resource resource = new Resource();
        resource.setName(resourceDTO.getName());
        resource.setUrl(resourceDTO.getUrl());
        resource.setSize(resourceDTO.getSize());

        resource.setLecture(lecture);

        resourceService.save(resource);
    }
    return  resourceDTO;
}

}
