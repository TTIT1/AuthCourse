package com.example.springjpa.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "COURSE")
public class Course {
    @Id
    @GeneratedValue
    private  Integer id;
    @Column(name = "t_title",unique = true,nullable = false)
   private String title;
    @Column(name = "d_description",unique = true,nullable = false)
    private String description;
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "course_author",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    @JsonIgnore
    private List<Author> authors = new ArrayList<>();

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Section> sections = new ArrayList<>();


}
