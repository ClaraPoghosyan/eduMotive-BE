package com.example.edumotive.controller;

import com.example.edumotive.model.*;
import com.example.edumotive.repository.AuthorRepository;
import org.springframework.http.ResponseEntity;
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

    @GetMapping
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
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

    @PostMapping
    public ResponseEntity<Author> createAuthor(@RequestBody Author author) {
        Author saved = authorRepository.save(author);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Author> updateAuthor(@PathVariable Integer id, @RequestBody Author request) {
        Author author = authorRepository.findById(id)
                .orElse(null);
        if (author == null) return ResponseEntity.notFound().build();

        author.setFullName(request.getFullName());
        author.setSpecialization(request.getSpecialization());
        author.setBiography(request.getBiography());
        author.setImageUrl(request.getImageUrl());
        author.setExperience(request.getExperience());
        author.setEducation(request.getEducation());
        author.setSkills(request.getSkills());
        author.setAbout(request.getAbout());
        author.setTeachingPhilosophy(request.getTeachingPhilosophy());
        author.setWhatYouLearn(request.getWhatYouLearn());
        author.setEmail(request.getEmail());

        return ResponseEntity.ok(authorRepository.save(author));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Integer id) {
        if (!authorRepository.existsById(id)) return ResponseEntity.notFound().build();
        authorRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}