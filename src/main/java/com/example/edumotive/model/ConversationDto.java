package com.example.edumotive.model;

import java.time.LocalDateTime;

public class ConversationDto {
    private Long id;
    private String studentEmail;
    private String instructorEmail;
    private String instructorName;
    private String lastMessage;
    private LocalDateTime lastMessageAt;
    private int unreadCount;

    public ConversationDto(Conversation c, int unreadCount) {
        this.id = c.getId();
        this.studentEmail = c.getStudentEmail();
        this.instructorEmail = c.getInstructorEmail();
        this.instructorName = c.getInstructorName();
        this.lastMessage = c.getLastMessage();
        this.lastMessageAt = c.getLastMessageAt();
        this.unreadCount = unreadCount;
    }

    public Long getId() { return id; }
    public String getStudentEmail() { return studentEmail; }
    public String getInstructorEmail() { return instructorEmail; }
    public String getInstructorName() { return instructorName; }
    public String getLastMessage() { return lastMessage; }
    public LocalDateTime getLastMessageAt() { return lastMessageAt; }
    public int getUnreadCount() { return unreadCount; }
}