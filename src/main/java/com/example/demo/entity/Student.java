package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@ToString
@Getter
@Setter
@Entity(name = "students")
// we should use from javax,because if we want to change from break  to different provider nothing will change

/*
    Hibernate is an implementation of the Java Persistence API,
    and where possible, you should use the standard annotations (in javax.persistence).
    This way, you could theoretically run your code on other JPA implementations.
 */

/*
    The main feature of Hibernate is to map the Java classes to database tables. Following are some key features of Hibernate :

Hibernate is an implementation of JPA guidelines.
It helps in mapping Java data types to SQL data types.
It is the contributor of JPA.
 */

@Table(name = "students",

uniqueConstraints = {
        @UniqueConstraint(name = "student_email_unique",columnNames = "email")
}
)
public class Student {

    @Id
    @SequenceGenerator(name = "student_sequence"
            , sequenceName = "student_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "student_sequence")
    private Long id;

    @Column(columnDefinition = "text ",nullable = false)
    private String firstName;

    @Column(columnDefinition = "text ",nullable = false)
    private String lastName;

    @Column(columnDefinition = "text ",nullable = false)
    private String email;

    private Integer age;

//    @OneToOne(
//            mappedBy = "student",
//            orphanRemoval = true
//    )
//    private StudentCard studentCard; // student card is the owning entity

    public Student(String firstName, String lastName, String email, Integer age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
    }

    public Student() {
    }

    /*
        By using the GenerationType.SEQUENCE strategy on the @GeneratedValue annotation, the JPA provider will try to use a database sequence object of the underlying database that supports this feature (e.g., Oracle, SQL Server, PostgreSQL, MariaDB).

If you are using MySQL, which doesn't support database sequence objects, then Hibernate is going to fall back to using the GenerationType.TABLE instead, which is undesirable since the TABLE generation performs badly.

So, don't use the GenerationType.SEQUENCE strategy with MySQL.
     */
}
