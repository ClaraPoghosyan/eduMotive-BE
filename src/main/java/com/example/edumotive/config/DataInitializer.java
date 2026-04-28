package com.example.edumotive.config;

import com.example.edumotive.model.User;
import com.example.edumotive.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public DataInitializer(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        User admin = userRepository.findByEmail("admin@edumotive.am").orElse(new User());
        admin.setEmail("admin@edumotive.am");
        if (admin.getPassword() == null) admin.setPassword(passwordEncoder.encode("admin123"));
        admin.setFullName("Admin");
        admin.setRole("ADMIN");
        if (admin.getCreatedAt() == null) admin.setCreatedAt(LocalDateTime.now());
        userRepository.save(admin);

        User instructor = userRepository.findByEmail("instructor@edumotive.am").orElse(new User());
        instructor.setEmail("instructor@edumotive.am");
        if (instructor.getPassword() == null) instructor.setPassword(passwordEncoder.encode("instructor123"));
        instructor.setFullName("Sample Instructor");
        instructor.setRole("INSTRUCTOR");
        if (instructor.getCreatedAt() == null) instructor.setCreatedAt(LocalDateTime.now());
        userRepository.save(instructor);

        userRepository.findAll().stream()
                .filter(u -> u.getRole() == null)
                .forEach(u -> { u.setRole("USER"); userRepository.save(u); });
    }
}
