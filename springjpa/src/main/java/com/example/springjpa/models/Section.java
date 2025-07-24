package com.example.springjpa.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "SECTION")
public class Section {
    @Id
    @GeneratedValue
    private  Integer id;
    @Column(name = "n_name",unique = true,nullable = false)
    private  String name;
    @Column(name = "o_order",unique = true,nullable = false)
    private  int order;
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
    @OneToMany(mappedBy = "section")
    private List< Lecture> lecture;
}

