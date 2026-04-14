package com.example.edumotive.controller;

import com.example.edumotive.model.*;
import com.example.edumotive.repository.CourseRepository;
import com.example.edumotive.repository.LessonVideoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseRepository courseRepository;
    private final LessonVideoRepository lessonVideoRepository;

    public CourseController(
            CourseRepository courseRepository,
            LessonVideoRepository lessonVideoRepository
    ) {
        this.courseRepository = courseRepository;
        this.lessonVideoRepository = lessonVideoRepository;
    }

    /**
     * GET /api/courses
     * Courses list (WITHOUT videos)
     */
    @GetMapping
    public List<CourseDto> getCourses() {
        return courseRepository.findAll()
                .stream()
                .map(course -> new CourseDto(
                        course.getId(),
                        course.getSlug(),
                        course.getTitle(),
                        course.getLanguage(),
                        course.getShortDescription(),
                        course.getDuration(),
                        course.getLessonsCount(),
                        course.getPriceAmd(),
                        course.getRating(),
                        course.getImageUrl(),
                        course.getCategory().getName(),
                        course.getAuthor().getFullName(),
                        course.getAuthor().getId(),
                        course.getFaqs()
                                .stream()
                                .map(faq -> new CourseFaqDto(
                                        faq.getQuestion(),
                                        faq.getAnswer()
                                ))
                                .toList()
                ))
                .toList();
    }

    /**
     * GET /api/courses/{id}
     * Course detail WITH videos
     */
    @GetMapping("/{id}")
    public CourseDetailsDto getCourseById(@PathVariable Long id) {

        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        List<LessonVideoDto> videos = lessonVideoRepository
                .findByCourseId(id)
                .stream()
                .map(video -> new LessonVideoDto(video.getYoutubeUrl()))
                .toList();

        return new CourseDetailsDto(
                course.getId(),
                course.getSlug(),
                course.getTitle(),
                course.getLanguage(),
                course.getShortDescription(),
                course.getDuration(),
                course.getLessonsCount(),
                course.getPriceAmd(),
                course.getRating(),
                course.getImageUrl(),
                course.getCategory().getName(),
                course.getAuthor().getFullName(),
                course.getAuthor().getId(),
                course.getFaqs()
                        .stream()
                        .map(faq -> new CourseFaqDto(
                                faq.getQuestion(),
                                faq.getAnswer()
                        ))
                        .toList(),
                videos
        );
    }

}
