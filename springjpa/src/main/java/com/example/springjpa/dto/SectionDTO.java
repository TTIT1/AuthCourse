package com.example.springjpa.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class SectionDTO {
    private Integer id;
    private String name;
    private int order;

    private Integer courseId;

    private List<LectureDTO> lectures; // nếu cần trả cả lectures
}
