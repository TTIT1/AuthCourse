package com.example.springjpa.controller;

import com.example.springjpa.dto.SectionDTO;
import com.example.springjpa.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v2")
public class SectionController {
    @Autowired
    SectionService sectionService;

    @GetMapping
    public List<SectionDTO> getAllSection(){
        return sectionService.getAllSection();
    }


}
