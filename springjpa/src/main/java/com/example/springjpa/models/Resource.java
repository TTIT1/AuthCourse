package com.example.springjpa.models;

import jakarta.persistence.*;
import lombok.Data;

import javax.swing.*;

@Entity
@Data
@Table(name = "Resource")
public class Resource {
    @Id
    @GeneratedValue
    private  Integer id;
    @Column(name = "n_name",unique = true,nullable = false)
    private  String name;
    @Column(name = "s_size")
    private  int size;
    @Column(name = "u_url")
    private String url;
    @OneToOne(cascade = CascadeType.ALL)// KHI QUAN HỆ 1 VS 1 THÊM NÀY ĐỂ LẤY ID LECTURE
    @JoinColumn(name = "lecture_id")
    private  Lecture lecture  ;
}
