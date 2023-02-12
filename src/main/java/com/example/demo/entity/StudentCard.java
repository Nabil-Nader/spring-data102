package com.example.demo.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Getter
@Setter

@Entity(name = "StudentCard")
@Table(
        name = "student_card",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "student_card_number_unique",
                        columnNames = "card_number"
                )
        }
)
public class StudentCard {

    @Id
    @SequenceGenerator(
            name = "student_card_sequence",
            sequenceName = "student_card_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "student_card_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    @Column(
            name = "card_number",
            nullable = false,
            length = 28
    )
    private String cardNumber;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "student_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "student_id_fk"
            )
    )
    private Student student;

    public StudentCard(String cardNumber, Student student) {
        this.cardNumber = cardNumber;
        this.student = student;
    }

    public StudentCard() {
    }

    @Override
    public String toString() {
        return "Student Card{" +
                "id=" + id +
                ", cardNumber='" + cardNumber + '\'' +
                ", student=" + student +
                '}';
    }
}
