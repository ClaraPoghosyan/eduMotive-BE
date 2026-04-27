package com.example.edumotive.model;

public record TestTopicDto(Long id, String title, String description,
                           int questionsCount, String difficulty) {}
