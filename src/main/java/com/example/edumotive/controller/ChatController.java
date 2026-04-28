package com.example.edumotive.controller;

import com.example.edumotive.model.*;
import com.example.edumotive.repository.AuthorRepository;
import com.example.edumotive.repository.ConversationRepository;
import com.example.edumotive.repository.MessageRepository;
import com.example.edumotive.repository.UserRepository;
import com.example.edumotive.security.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/chat")
public class ChatController {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final AuthorRepository authorRepository;
    private final ConversationRepository conversationRepository;
    private final MessageRepository messageRepository;

    public ChatController(JwtUtil jwtUtil, UserRepository userRepository,
                          AuthorRepository authorRepository,
                          ConversationRepository conversationRepository,
                          MessageRepository messageRepository) {
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
        this.authorRepository = authorRepository;
        this.conversationRepository = conversationRepository;
        this.messageRepository = messageRepository;
    }

    private String extractEmail(String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) return null;
        String token = authHeader.substring(7);
        if (!jwtUtil.isValid(token)) return null;
        return jwtUtil.extractEmail(token);
    }

    @GetMapping("/instructors")
    public ResponseEntity<?> getInstructors(@RequestHeader(value = "Authorization", required = false) String authHeader) {
        String callerEmail = extractEmail(authHeader);
        if (callerEmail == null) return ResponseEntity.status(401).body("Unauthorized");

        List<Author> instructors = authorRepository.findAll();
        return ResponseEntity.ok(instructors);
    }

    @PostMapping("/conversations/by-author/{authorId}")
    public ResponseEntity<?> createConversationByAuthor(
            @RequestHeader(value = "Authorization", required = false) String authHeader,
            @PathVariable Integer authorId) {

        String callerEmail = extractEmail(authHeader);
        if (callerEmail == null) return ResponseEntity.status(401).body("Unauthorized");

        Author author = authorRepository.findById(authorId).orElse(null);
        if (author == null) return ResponseEntity.notFound().build();
        if (author.getEmail() == null || author.getEmail().isBlank())
            return ResponseEntity.badRequest().body("This author does not have a linked account yet");

        Conversation conversation = conversationRepository
                .findByStudentEmailAndInstructorEmail(callerEmail, author.getEmail())
                .orElseGet(() -> {
                    Conversation c = new Conversation();
                    c.setStudentEmail(callerEmail);
                    c.setInstructorEmail(author.getEmail());
                    c.setInstructorName(author.getFullName());
                    c.setLastMessageAt(LocalDateTime.now());
                    return conversationRepository.save(c);
                });

        return ResponseEntity.ok(conversation);
    }

    @PostMapping("/conversations")
    public ResponseEntity<?> createOrGetConversation(
            @RequestHeader(value = "Authorization", required = false) String authHeader,
            @RequestBody Map<String, String> body) {

        String callerEmail = extractEmail(authHeader);
        if (callerEmail == null) return ResponseEntity.status(401).body("Unauthorized");

        String instructorEmail = body.get("instructorEmail");
        if (instructorEmail == null || instructorEmail.isBlank())
            return ResponseEntity.badRequest().body("instructorEmail is required");

        Conversation conversation = conversationRepository
                .findByStudentEmailAndInstructorEmail(callerEmail, instructorEmail)
                .orElseGet(() -> {
                    User instructor = userRepository.findByEmail(instructorEmail).orElse(null);
                    Conversation c = new Conversation();
                    c.setStudentEmail(callerEmail);
                    c.setInstructorEmail(instructorEmail);
                    c.setInstructorName(instructor != null ? instructor.getFullName() : instructorEmail);
                    c.setLastMessageAt(LocalDateTime.now());
                    return conversationRepository.save(c);
                });

        return ResponseEntity.ok(conversation);
    }

    @GetMapping("/conversations")
    public ResponseEntity<?> getConversations(
            @RequestHeader(value = "Authorization", required = false) String authHeader) {

        String callerEmail = extractEmail(authHeader);
        if (callerEmail == null) return ResponseEntity.status(401).body("Unauthorized");

        List<ConversationDto> result = conversationRepository
                .findByStudentEmailOrInstructorEmailOrderByLastMessageAtDesc(callerEmail, callerEmail)
                .stream()
                .map(c -> {
                    int unread = messageRepository.countByConversationIdAndSenderEmailNotAndIsReadFalse(c.getId(), callerEmail);
                    return new ConversationDto(c, unread);
                })
                .toList();

        return ResponseEntity.ok(result);
    }

    @GetMapping("/conversations/{id}/messages")
    public ResponseEntity<?> getMessages(
            @RequestHeader(value = "Authorization", required = false) String authHeader,
            @PathVariable Long id) {

        String callerEmail = extractEmail(authHeader);
        if (callerEmail == null) return ResponseEntity.status(401).body("Unauthorized");

        List<Message> messages = messageRepository.findByConversationIdOrderBySentAtAsc(id);

        messages.stream()
                .filter(m -> !m.getSenderEmail().equals(callerEmail) && !m.isRead())
                .forEach(m -> {
                    m.setRead(true);
                    messageRepository.save(m);
                });

        return ResponseEntity.ok(messages);
    }

    @PostMapping("/conversations/{id}/messages")
    public ResponseEntity<?> sendMessage(
            @RequestHeader(value = "Authorization", required = false) String authHeader,
            @PathVariable Long id,
            @RequestBody Map<String, String> body) {

        String callerEmail = extractEmail(authHeader);
        if (callerEmail == null) return ResponseEntity.status(401).body("Unauthorized");

        String content = body.get("content");
        if (content == null || content.isBlank()) return ResponseEntity.badRequest().body("content is required");

        Conversation conversation = conversationRepository.findById(id).orElse(null);
        if (conversation == null) return ResponseEntity.notFound().build();

        User sender = userRepository.findByEmail(callerEmail).orElse(null);

        Message message = new Message();
        message.setConversationId(id);
        message.setSenderEmail(callerEmail);
        message.setSenderName(sender != null ? sender.getFullName() : callerEmail);
        message.setContent(content);
        message.setSentAt(LocalDateTime.now());
        messageRepository.save(message);

        conversation.setLastMessage(content);
        conversation.setLastMessageAt(LocalDateTime.now());
        conversationRepository.save(conversation);

        return ResponseEntity.ok(message);
    }

    @PutMapping("/conversations/{id}/read")
    public ResponseEntity<?> markAsRead(
            @RequestHeader(value = "Authorization", required = false) String authHeader,
            @PathVariable Long id) {

        String callerEmail = extractEmail(authHeader);
        if (callerEmail == null) return ResponseEntity.status(401).body("Unauthorized");

        messageRepository.findByConversationIdOrderBySentAtAsc(id).stream()
                .filter(m -> !m.getSenderEmail().equals(callerEmail) && !m.isRead())
                .forEach(m -> {
                    m.setRead(true);
                    messageRepository.save(m);
                });

        return ResponseEntity.ok("Marked as read");
    }
}