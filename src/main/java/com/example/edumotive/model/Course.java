package com.example.edumotive.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String slug;

    @Column(nullable = false)
    private String title;

    @Column(name = "language")
    private String language;

    @Column(name = "short_description")
    private String shortDescription;

    @Column
    private String duration;

    @Column(name = "lessons_count")
    private Integer lessonsCount;

    @Column(name = "price_amd")
    private Integer priceAmd;

    @Column(name = "image_url")
    private String imageUrl;

    @Column
    private Integer rating;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    /* ---------- GETTERS ---------- */

    public Long getId() {
        return id;
    }

    public String getSlug() {
        return slug;
    }

    public String getTitle() {
        return title;
    }

    public String getLanguage() {
        return language;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getDuration() {
        return duration;
    }

    public Integer getLessonsCount() {
        return lessonsCount;
    }

    public Integer getPriceAmd() {
        return priceAmd;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Integer getRating() {
        return rating;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private CourseCategory category;

    public CourseCategory getCategory() {
        return category;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private Author author;

    public Author getAuthor() {
        return author;
    }

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CourseFaq> faqs;


    public List<CourseFaq> getFaqs() {
        return faqs;
    }
}
