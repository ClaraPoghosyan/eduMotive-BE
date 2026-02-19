package com.example.edumotive.controller;

import com.example.edumotive.model.BlogPost;
import com.example.edumotive.repository.BlogPostRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/blogs")
public class BlogController {

    private final BlogPostRepository blogPostRepository;

    public BlogController(BlogPostRepository blogPostRepository) {
        this.blogPostRepository = blogPostRepository;
    }

    @GetMapping
    public List<BlogPost> getAllBlogs() {
        return blogPostRepository.findAll();
    }
}
