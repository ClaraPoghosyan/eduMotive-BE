package com.example.edumotive.model;

import java.util.List;

public record QuizQuestionRequest(String text, List<String> options, int correctIndex) {}
