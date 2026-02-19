package com.example.edumotive.controller;

import com.example.edumotive.model.Faq;
import com.example.edumotive.repository.FaqRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/faq")
public class FaqController {

    private final FaqRepository faqRepository;

    public FaqController(FaqRepository faqRepository) {
        this.faqRepository = faqRepository;
    }

    @GetMapping
    public List<Faq> getAllFaq() {
        return faqRepository.findAll();
    }
}
