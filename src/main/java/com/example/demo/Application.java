package com.example.demo;

import com.example.demo.entity.Student;
import com.example.demo.entity.StudentCard;
import com.example.demo.reposiory.StudentCardRepository;
import com.example.demo.reposiory.StudentRepository;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(
            StudentRepository studentRepository,
            StudentCardRepository studentCardRepository) {
        return args -> {
           /* Faker faker = new Faker();

            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            String email = String.format("%s.%s@amigoscode.edu", firstName, lastName);
            Student student = new Student(
                    firstName,
                    lastName,
                    email,
                    faker.number().numberBetween(17, 55));*/

            generateRandomStudents(studentRepository,studentCardRepository);

//            studentRepository.findById(1L)
//                    .ifPresent(System.out::println);
//
//            studentCardRepository.findById(1L)
//                    .ifPresent(System.out::println);
//
////            studentRepository.deleteById(1L);

        };
    }

    private void generateRandomStudents(StudentRepository studentRepository,StudentCardRepository studentCardRepository) {
        Faker faker = new Faker();
        for (int i = 0; i < 20; i++) {
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            String email = String.format("%s.%s@amigoscode.edu", firstName, lastName);
            Student student = new Student(
                    firstName,
                    lastName,
                    email,
                    faker.number().numberBetween(17, 55));

            StudentCard studentIdCard = new StudentCard(
                    faker.phoneNumber().phoneNumber(),
                    student);

            studentCardRepository.save(studentIdCard);

//            studentRepository.save(student);
        }
    }
}
