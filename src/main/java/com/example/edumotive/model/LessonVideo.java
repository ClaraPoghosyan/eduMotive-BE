package com.example.edumotive.model;

import jakarta.persistence.*;

@Entity
@Table(name = "lesson_videos")
public class LessonVideo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "youtube_url")
    private String youtubeUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    public Integer getId() {
        return id;
    }

    public String getYoutubeUrl() {
        return youtubeUrl;
    }
}
