/**
 * Developed By S.A. WEERASINGHE - PS/2019/259
 *  * Quiz Page Development
 */


package com.example.quiz.operation;

import com.example.quiz.model.Quiz;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;
import java.util.Optional;



public interface QuizOperation {

    /** To create a new quiz **/
    Quiz createQuiz(Quiz q);

    /** To get all the existing questions currently available **/
    List<Quiz> getAllQuestions();

    /** To get the available questions based on their id */
    Optional<Quiz> getQuestionById(Long id);

    /** To get all the available subjects */
    List<String> getAllSubjects();

    /** To get all the grades that quizzes are available **/
    List<String> getAllGrades();

    /** To provide the accessibility for updating the quizzes */
    Quiz updateQuiz(Long id, Quiz q) throws ChangeSetPersister.NotFoundException;

    /** To delete the existing quizzes */
    void  deleteQuestion(Long id);

    List<Quiz> getQuizForStudent(Integer numOfQuestions, String subject);

    List<Quiz> getQuizForStudentByGrade(Integer numOfQuestions, String grade);


}