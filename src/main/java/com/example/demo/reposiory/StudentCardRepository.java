package com.example.demo.reposiory;

import com.example.demo.entity.StudentCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentCardRepository extends JpaRepository<StudentCard, Long> {


}
