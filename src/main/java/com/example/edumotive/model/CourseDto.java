package com.example.edumotive.model;

import java.util.List;

public class CourseDto {

    private Long id;
    private String slug;
    private String title;
    private String language;
    private String shortDescription;
    private String duration;
    private Integer lessonsCount;
    private Integer priceAmd;
    private Integer rating;
    private String imageUrl;
    private String groupName;
    private String authorName;
    private List<CourseFaqDto> faqs;


    public CourseDto(
            Long id,
            String slug,
            String title,
            String language,
            String shortDescription,
            String duration,
            Integer lessonsCount,
            Integer priceAmd,
            Integer rating,
            String imageUrl,
            String groupName,
            String authorName,
            List<CourseFaqDto> faqs
    ) {
        this.id = id;
        this.slug = slug;
        this.title = title;
        this.language = language;
        this.shortDescription = shortDescription;
        this.duration = duration;
        this.lessonsCount = lessonsCount;
        this.priceAmd = priceAmd;
        this.rating = rating;
        this.imageUrl = imageUrl;
        this.groupName = groupName;
        this.authorName = authorName;
        this.faqs = faqs;

    }

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

    public Integer getRating() {
        return rating;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getGroupName() {
        return groupName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public List<CourseFaqDto> getFaqs() {
        return faqs;
    }

}
