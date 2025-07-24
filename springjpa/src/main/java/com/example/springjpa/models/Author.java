package com.example.springjpa.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name ="AUTHOR_TBL")
public class Author {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(name = "f_name", length = 50, nullable = false)
    private String firstName;
    @Column(name = "l_name", length = 50, nullable = false)
    private String lastName;
    @Column(name = "g_email", unique = true, nullable = false, length = 255)
    private String email;
    private int age;
    @ManyToMany(mappedBy = "authors")
    @JsonIgnore
    private List<Course> courses = new ArrayList<>();

    


}
