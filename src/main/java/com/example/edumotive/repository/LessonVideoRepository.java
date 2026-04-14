package com.example.edumotive.repository;

import com.example.edumotive.model.LessonVideo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LessonVideoRepository extends JpaRepository<LessonVideo, Integer> {

    List<LessonVideo> findByCourseId(Long courseId);
}
