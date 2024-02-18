/**
 * Developed by S.A.WEERASINGHE - PS/2019/259
 * Quiz Page Development
 */

package com.example.quiz.repository;
import com.example.quiz.model.Quiz;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface QuizRepository extends JpaRepository<Quiz, Long> {

    @Query("SELECT DISTINCT q.subject FROM Quiz q")

    List<String> findSpecifiedSubject();
    Page<Quiz> findBySubject(String subject, Pageable pageable);



}


