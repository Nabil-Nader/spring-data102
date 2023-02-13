package com.example.demo;

import com.example.demo.entity.Book;
import com.example.demo.entity.Student;
import com.example.demo.entity.StudentCard;
import com.example.demo.reposiory.BookRepository;
import com.example.demo.reposiory.StudentCardRepository;
import com.example.demo.reposiory.StudentRepository;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;


@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(
            StudentRepository studentRepository,
            StudentCardRepository studentCardRepository,
            BookRepository bookRepository) {
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

//            generateRandomStudents(studentRepository,studentCardRepository);
            saveStudent(studentRepository);
//            genrateListOfBook(studentRepository,bookRepository);

//            studentRepository.findById(1L)
//                    .ifPresent(System.out::println);
//
//            studentCardRepository.findById(1L)
//                    .ifPresent(System.out::println);
//
////            studentRepository.deleteById(1L);

        };
    }

    private void saveStudent(StudentRepository studentRepository) {
        //
        Faker faker = new Faker();
        for (int i = 0; i < 5; i++) {
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            String email = String.format("%s.%s@amigoscode.edu", firstName, lastName);
            Student student = new Student(
                    firstName,
                    lastName,
                    email,
                    faker.number().numberBetween(17, 55));

            for (int j = 0; j < 2; j++) {
                String bookName = faker.book().title();
                Book b = new Book(bookName);
                student.addBook(b);
//                studentRepository.save(student);

            }
            studentRepository.save(student);
        }

    }

    private void generateRandomStudents(StudentRepository studentRepository, StudentCardRepository studentCardRepository) {
        Faker faker = new Faker();
        for (int i = 0; i < 5; i++) {
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

    private void genrateListOfBook(StudentRepository studentRepository, BookRepository bookRepository) {

        //

        Faker faker = new Faker();


        List<Student> allStudent = studentRepository.findAll();

        for (Student s : allStudent) {

            for (int i = 0; i < 2; i++) {
                String bookName = faker.book().title();
                Book b = new Book(bookName);
//            b.setStudent(s);
//            bookRepository.save(b);
                s.addBook(b);
                studentRepository.save(s);

            }
        }

    }
}
