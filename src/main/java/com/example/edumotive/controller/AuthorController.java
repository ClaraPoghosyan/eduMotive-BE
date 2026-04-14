package com.example.edumotive.controller;

import com.example.edumotive.model.*;
import com.example.edumotive.repository.AuthorRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/authors")
public class AuthorController {

    private final AuthorRepository authorRepository;

    public AuthorController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @GetMapping("/{id}")
    public AuthorDetailsDto getAuthorById(@PathVariable Integer id) {


        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Author not found"));

        List<AuthorCourseDto> courses = author.getCourses()
                .stream()
                .map(course -> new AuthorCourseDto(
                        course.getId(),
                        course.getTitle(),
                        course.getSlug()
                ))
                .toList();

        return new AuthorDetailsDto(
                author.getId(),
                author.getFullName(),
                author.getSpecialization(),
                author.getBiography(),
                author.getImageUrl(),
                author.getExperience(),
                author.getEducation(),
                author.getSkills(),
                author.getAbout(),
                author.getTeachingPhilosophy(),
                author.getWhatYouLearn(),
                courses
        );
    }
}