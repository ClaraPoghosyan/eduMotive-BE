package com.example.edumotive.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "specialization")
    private String specialization;

    @Column(name = "biography", columnDefinition = "TEXT")
    private String biography;

    @Column(name = "image_url")
    private String imageUrl;

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    private List<Course> courses;

    @Column
    private String experience;

    @Column(columnDefinition = "TEXT")
    private String education;

    @Column(columnDefinition = "TEXT")
    private String skills;

    @Column(columnDefinition = "TEXT")
    private String about;

    @Column(name = "teaching_philosophy", columnDefinition = "TEXT")
    private String teachingPhilosophy;

    @Column(name = "what_you_learn", columnDefinition = "TEXT")
    private String whatYouLearn;

    /* ---------------- GETTERS ---------------- */

    public Integer getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getSpecialization() {
        return specialization;
    }

    public String getBiography() {
        return biography;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public List<Course> getCourses() {
        return courses;
    }
    public String getExperience() {
        return experience;
    }

    public String getEducation() {
        return education;
    }

    public String getSkills() {
        return skills;
    }

    public String getAbout() {
        return about;
    }

    public String getTeachingPhilosophy() {
        return teachingPhilosophy;
    }

    public String getWhatYouLearn() {
        return whatYouLearn;
    }

}