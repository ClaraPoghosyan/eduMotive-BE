package com.example.edumotive.model;

import java.util.List;

public record QuizTestRequest(String title, String topic, String description,
                              String difficulty, List<QuizQuestionRequest> questions) {}
