package com.example.edumotive.controller;

import com.example.edumotive.model.Question;
import com.example.edumotive.model.QuestionRequest;
import com.example.edumotive.repository.QuestionRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/questions")
public class QuestionController {

    private final QuestionRepository questionRepository;

    public QuestionController(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @GetMapping
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<?> sendQuestion(@RequestBody QuestionRequest request) {
        if (request.getFullName() == null || request.getFullName().isBlank() ||
            request.getEmail() == null || request.getEmail().isBlank() ||
            request.getTitle() == null || request.getTitle().isBlank() ||
            request.getMessage() == null || request.getMessage().isBlank()) {
            return ResponseEntity.badRequest().body("All fields are required");
        }

        Question question = new Question();
        question.setFullName(request.getFullName());
        question.setEmail(request.getEmail());
        question.setTitle(request.getTitle());
        question.setMessage(request.getMessage());
        question.setCreatedAt(LocalDateTime.now());

        questionRepository.save(question);
        return ResponseEntity.ok("Question submitted successfully");
    }
}