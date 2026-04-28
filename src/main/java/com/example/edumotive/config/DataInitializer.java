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
        if (userRepository.findByEmail("admin@edumotive.am").isEmpty()) {
            User admin = new User();
            admin.setEmail("admin@edumotive.am");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setFullName("Admin");
            admin.setRole("ADMIN");
            admin.setCreatedAt(LocalDateTime.now());
            userRepository.save(admin);
        }

        if (userRepository.findByEmail("instructor@edumotive.am").isEmpty()) {
            User instructor = new User();
            instructor.setEmail("instructor@edumotive.am");
            instructor.setPassword(passwordEncoder.encode("instructor123"));
            instructor.setFullName("Sample Instructor");
            instructor.setRole("INSTRUCTOR");
            instructor.setCreatedAt(LocalDateTime.now());
            userRepository.save(instructor);
        }
    }
}
