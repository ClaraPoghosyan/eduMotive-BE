package com.example.edumotive.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
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

    /* ---------------- GETTERS & SETTERS ---------------- */

    public Integer getId() { return id; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }

    public String getBiography() { return biography; }
    public void setBiography(String biography) { this.biography = biography; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public List<Course> getCourses() { return courses; }

    public String getExperience() { return experience; }
    public void setExperience(String experience) { this.experience = experience; }

    public String getEducation() { return education; }
    public void setEducation(String education) { this.education = education; }

    public String getSkills() { return skills; }
    public void setSkills(String skills) { this.skills = skills; }

    public String getAbout() { return about; }
    public void setAbout(String about) { this.about = about; }

    public String getTeachingPhilosophy() { return teachingPhilosophy; }
    public void setTeachingPhilosophy(String teachingPhilosophy) { this.teachingPhilosophy = teachingPhilosophy; }

    public String getWhatYouLearn() { return whatYouLearn; }
    public void setWhatYouLearn(String whatYouLearn) { this.whatYouLearn = whatYouLearn; }

}