package com.example.edumotive.repository;

import com.example.edumotive.model.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ConversationRepository extends JpaRepository<Conversation, Long> {
    List<Conversation> findByStudentEmailOrInstructorEmailOrderByLastMessageAtDesc(String studentEmail, String instructorEmail);
    Optional<Conversation> findByStudentEmailAndInstructorEmail(String studentEmail, String instructorEmail);
}