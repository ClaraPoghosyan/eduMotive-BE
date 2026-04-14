package com.example.edumotive.model;

import java.util.List;

public class AuthorDetailsDto {

    private Integer id;
    private String fullName;
    private String specialization;
    private String biography;
    private String imageUrl;

    private String experience;
    private String education;
    private String skills;
    private String about;
    private String teachingPhilosophy;
    private String whatYouLearn;

    private List<AuthorCourseDto> courses;

    public AuthorDetailsDto(
            Integer id,
            String fullName,
            String specialization,
            String biography,
            String imageUrl,
            String experience,
            String education,
            String skills,
            String about,
            String teachingPhilosophy,
            String whatYouLearn,
            List<AuthorCourseDto> courses
    ) {
        this.id = id;
        this.fullName = fullName;
        this.specialization = specialization;
        this.biography = biography;
        this.imageUrl = imageUrl;
        this.experience = experience;
        this.education = education;
        this.skills = skills;
        this.about = about;
        this.teachingPhilosophy = teachingPhilosophy;
        this.whatYouLearn = whatYouLearn;
        this.courses = courses;
    }

    // getters
    public Integer getId() { return id; }
    public String getFullName() { return fullName; }
    public String getSpecialization() { return specialization; }
    public String getBiography() { return biography; }
    public String getImageUrl() { return imageUrl; }
    public List<AuthorCourseDto> getCourses() { return courses; }
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