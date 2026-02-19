package com.example.edumotive.model;

public class LessonVideoDto {

    private String youtubeUrl;

    public LessonVideoDto(String youtubeUrl) {
        this.youtubeUrl = youtubeUrl;
    }

    public String getYoutubeUrl() {
        return youtubeUrl;
    }
}
