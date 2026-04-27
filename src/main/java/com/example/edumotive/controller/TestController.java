package com.example.edumotive.controller;

import com.example.edumotive.model.*;
import com.example.edumotive.repository.QuizTestRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/tests")
public class TestController {

    private final QuizTestRepository testRepository;

    public TestController(QuizTestRepository testRepository) {
        this.testRepository = testRepository;
    }

    @GetMapping
    public List<TestTopicDto> getTopics() {
        return testRepository.findAll().stream()
                .map(t -> new TestTopicDto(
                        t.getId(),
                        t.getTitle(),
                        t.getDescription(),
                        t.getQuestions() != null ? t.getQuestions().size() : 0,
                        t.getDifficulty()
                ))
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TestDetailDto> getTest(@PathVariable Long id) {
        return testRepository.findById(id)
                .map(t -> {
                    List<QuestionDto> questions = t.getQuestions().stream()
                            .map(q -> new QuestionDto(q.getId(), q.getText(),
                                    q.getOptions().stream().distinct().toList()))
                            .toList();
                    return ResponseEntity.ok(new TestDetailDto(t.getId(), t.getTitle(), questions));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<QuizTest> createTest(@RequestBody QuizTestRequest req) {
        QuizTest test = new QuizTest();
        test.setTitle(req.title());
        test.setTopic(req.topic());
        test.setDescription(req.description());
        test.setDifficulty(req.difficulty());

        if (req.questions() != null) {
            List<QuizQuestion> questions = req.questions().stream().map(q -> {
                QuizQuestion question = new QuizQuestion();
                question.setText(q.text());
                question.setOptions(q.options());
                question.setCorrectIndex(q.correctIndex());
                question.setTest(test);
                return question;
            }).toList();
            test.setQuestions(questions);
        }
        return ResponseEntity.ok(testRepository.save(test));
    }

    @PutMapping("/{id}")
    public ResponseEntity<QuizTest> updateTest(@PathVariable Long id,
                                               @RequestBody QuizTestRequest req) {
        QuizTest test = testRepository.findById(id).orElse(null);
        if (test == null) return ResponseEntity.notFound().build();

        test.setTitle(req.title());
        test.setTopic(req.topic());
        test.setDescription(req.description());
        test.setDifficulty(req.difficulty());

        test.getQuestions().clear();
        if (req.questions() != null) {
            req.questions().forEach(q -> {
                QuizQuestion question = new QuizQuestion();
                question.setText(q.text());
                question.setOptions(q.options());
                question.setCorrectIndex(q.correctIndex());
                question.setTest(test);
                test.getQuestions().add(question);
            });
        }

        return ResponseEntity.ok(testRepository.save(test));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTest(@PathVariable Long id) {
        if (!testRepository.existsById(id)) return ResponseEntity.notFound().build();
        testRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/submit")
    public ResponseEntity<TestResultDto> submit(@PathVariable Long id,
                                                @RequestBody SubmitRequest req) {
        QuizTest test = testRepository.findById(id).orElse(null);
        if (test == null) return ResponseEntity.notFound().build();

        List<QuizQuestion> questions = test.getQuestions();
        int total = questions.size();
        int score = 0;

        for (int i = 0; i < Math.min(total, req.answers().size()); i++) {
            if (questions.get(i).getCorrectIndex() == req.answers().get(i)) {
                score++;
            }
        }

        int percentage = total == 0 ? 0 : (score * 100) / total;

        String level;
        String feedback;
        if (percentage <= 40) {
            level = "Beginner";
            feedback = "Keep practicing! Review the basics and try again.";
        } else if (percentage <= 65) {
            level = "Intermediate";
            feedback = "Good effort! You have a solid foundation but there's room to grow.";
        } else if (percentage <= 85) {
            level = "Advanced";
            feedback = "Great job! You have strong knowledge of the topic.";
        } else {
            level = "Expert";
            feedback = "Excellent! You have mastered this topic.";
        }

        return ResponseEntity.ok(new TestResultDto(score, total, percentage, level, feedback));
    }
}
