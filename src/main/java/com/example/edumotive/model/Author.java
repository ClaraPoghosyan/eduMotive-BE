package com.example.edumotive.model;

import jakarta.persistence.*;

@Entity
@Table(name = "authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    public Integer getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }
}
