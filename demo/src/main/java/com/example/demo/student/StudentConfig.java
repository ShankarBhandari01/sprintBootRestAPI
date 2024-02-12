package com.example.demo.student;

import com.example.demo.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

import static java.time.Month.JULY;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
        return args -> {
            Student shanakr = new Student(
                    "Shankar",
                    LocalDate.of(2000, JULY, 31),
                    "sankar@gmail.com");

            Student bhandari = new Student(
                    "bhandari",
                    LocalDate.of(2004, JULY, 31),
                    "bhandari@gmail.com");
            studentRepository.saveAll(
                    List.of(shanakr, bhandari)
            );
        };
    }

}
