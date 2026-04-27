package com.example.edumotive.model;

public record TestResultDto(int score, int total, int percentage,
                            String level, String feedback) {}
