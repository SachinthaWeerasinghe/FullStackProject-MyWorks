/**
 * Developed By S.A. WEERASINGHE - PS/2019/259
 *  * Quiz Page Development
 */


package com.example.quiz.service;

import com.example.quiz.model.Quiz;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;
import java.util.Optional;



public interface QuizService {

    /** To create a new quiz **/
    Quiz createQuiz(Quiz q);

    /** To get all the existing questions currently available **/
    List<Quiz> getAllQuestions();

    /** To get the available questions based on their id */
    Optional<Quiz> getQuestionById(Long id);

    /** To get all the available subjects */
    List<String> getAllSubjects();

    /** To provide the accessibility for updating the quizzes */
    Quiz updateQuiz(Long id, Quiz q) throws ChangeSetPersister.NotFoundException;

    /** To delete the existing quizzes */
    void  deleteQuestion(Long id);

    List<Quiz> getQuizForStudent(Integer numOfQuestions, String subject);

}