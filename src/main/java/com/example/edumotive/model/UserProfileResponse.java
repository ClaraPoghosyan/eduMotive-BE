package com.example.edumotive.model;

import java.time.LocalDateTime;

public class UserProfileResponse {
    private Integer id;
    private String email;
    private String fullName;
    private String role;
    private LocalDateTime createdAt;

    public UserProfileResponse(Integer id, String email, String fullName, String role, LocalDateTime createdAt) {
        this.id = id;
        this.email = email;
        this.fullName = fullName;
        this.role = role;
        this.createdAt = createdAt;
    }

    public Integer getId() { return id; }
    public String getEmail() { return email; }
    public String getFullName() { return fullName; }
    public String getRole() { return role; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}
