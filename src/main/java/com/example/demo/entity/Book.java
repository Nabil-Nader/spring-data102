package com.example.demo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity(name = "Book")
@Table(name = "book")
@Getter
@Setter
@NoArgsConstructor
public class Book {

    @Id
    @SequenceGenerator(
            name = "book_sequence",
            sequenceName = "book_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "book_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;



    private String bookName;

    @Column(
            name = "created_at",
            nullable = false,
            columnDefinition = "TIMESTAMP WITHOUT TIME ZONE"
    )
    private LocalDate createAt;

    public Book(String bookName) {
        this.bookName = bookName;
        this.createAt = LocalDate.now();
    }


    // also book we me the owning entity  this is uni directional [when student does not have any clue about book]
    //many book one student
  /*  @ManyToOne
    @JoinColumn(
            name = "student_id",
            referencedColumnName = "id", // this is on the student side
            foreignKey = @ForeignKey(name = "student_book_id_fk")

    )
    private Student student;    // book will be the owning side
*/

    @ManyToOne
    @JoinColumn(
            name = "student_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "student_book_fk"
            )
    )
    private Student student;
}
