package com.example.edumotive.model;

import java.util.List;

public record TestDetailDto(Long id, String title, List<QuestionDto> questions) {}
