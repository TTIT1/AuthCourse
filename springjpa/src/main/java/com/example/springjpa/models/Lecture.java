package com.example.springjpa.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "LECTURE")
public class Lecture {

    @Id
    @GeneratedValue
    private Integer id;
    @Column(name = "n_name", unique = true, nullable = false)
    private String name;
    @ManyToOne
    @JoinColumn(name = "section_id")
    private Section section;
    @OneToOne(mappedBy = "lecture")
    private Resource resource;
}
