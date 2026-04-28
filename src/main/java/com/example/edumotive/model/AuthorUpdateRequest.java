package com.example.edumotive.model;

public class AuthorUpdateRequest {
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
    private String email;
    private String password;

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }

    public String getBiography() { return biography; }
    public void setBiography(String biography) { this.biography = biography; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

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

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}