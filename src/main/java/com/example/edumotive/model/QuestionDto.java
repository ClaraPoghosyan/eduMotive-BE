package com.example.edumotive.model;

import java.util.List;

public record QuestionDto(Long id, String text, List<String> options) {}
