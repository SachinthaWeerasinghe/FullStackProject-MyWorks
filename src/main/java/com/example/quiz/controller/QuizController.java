/**
 * Developed By S.A. WEERASINGHE - PS/2019/259
 */

package com.example.quiz.controller;

import com.example.quiz.model.Quiz;
import com.example.quiz.operation.QuizOperation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;


@CrossOrigin("http://localhost:5173")
@RestController
@RequestMapping("/api/quiz")
@RequiredArgsConstructor



public class QuizController {
    private final QuizOperation quizOperation;

    /** To Create a new question for a quiz **/
    @PostMapping("/create-new-quiz")
    public ResponseEntity<Quiz> createQuiz(@Valid @RequestBody Quiz question){
        Quiz createdQuiz = quizOperation.createQuiz(question);
        return ResponseEntity.status(CREATED).body(createdQuiz);
    }


    /** To get all the questions available in the database **/
    @GetMapping("/all-questions")
    public ResponseEntity<List<Quiz>> getAllQuestions(){
        List<Quiz> questions = quizOperation.getAllQuestions();
        return ResponseEntity.ok(questions);
    }

    /** To get a question by specifying its id **/
    @GetMapping("/question/{id}")
    public ResponseEntity<Quiz> getQuestionById(@PathVariable Long id) throws ChangeSetPersister.NotFoundException {
        Optional<Quiz> ques = quizOperation.getQuestionById(id);
        if (ques.isPresent()){
            return ResponseEntity.ok(ques.get());
        }else {
            throw new ChangeSetPersister.NotFoundException();
        }
    }

    /** To update an existing Quiz **/
    @PutMapping("/question/{id}/update")
    public ResponseEntity<Quiz> updateQuiz(
            @PathVariable Long id, @RequestBody Quiz question) throws ChangeSetPersister.NotFoundException {
        Quiz updatedQuiz = quizOperation.updateQuiz(id, question);
        return ResponseEntity.ok(updatedQuiz);
    }

    /** To Delete an existing Quiz question **/
    @DeleteMapping("/question/{id}/delete")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Long id){
        quizOperation.deleteQuestion(id);
        return ResponseEntity.noContent().build();
    }

    /**
        To get all the subjects
     **/
    @GetMapping("/subjects")
    public ResponseEntity<List<String>> getAllSubjects(){
        List<String> subjects = quizOperation.getAllSubjects();
        return ResponseEntity.ok(subjects);
    }

    /**
     To get an Quiz for the student
     **/
    @GetMapping("/Quiz/fetch-quiz-for-student")
    public ResponseEntity<List<Quiz>> getQuizForStudent(
            @RequestParam Integer numOfQuestions, @RequestParam String subject){
        List<Quiz> allQuestions = quizOperation.getQuizForStudent(numOfQuestions, subject);

        List<Quiz> mutableQuestions = new ArrayList<>(allQuestions);
        Collections.shuffle(mutableQuestions);        /** To Shuffle the questions **/

        int availableQuestions = Math.min(numOfQuestions, mutableQuestions.size());

        /** To available questions become randomized when retaking the Quiz **/
        List<Quiz> randomQues = mutableQuestions.subList(0, availableQuestions);
        return ResponseEntity.ok(randomQues);
    }

    /**
        To get a Quiz for the student according to their grade
     **/
    @GetMapping("/quiz/fetch-quiz-for-user-by-grade")
    public ResponseEntity<List<Quiz>> getQuizForStudentByGrade(
            @RequestParam Integer numOfQuestions, @RequestParam String grade){
        List<Quiz> allQuestions = quizOperation.getQuizForStudent(numOfQuestions, grade);

        List<Quiz> mutableQuestions = new ArrayList<>(allQuestions);
        Collections.shuffle(mutableQuestions);

        /** Calculate the number of available questions based on
          the minimum of numberOfQuestion and the size of the mutableQuestions
         */
        int availableQuestions = Math.min(numOfQuestions, mutableQuestions.size());

        /** To available questions become randomized when retaking the Quiz **/
        List<Quiz> randomQues = mutableQuestions.subList(0, availableQuestions);
        return ResponseEntity.ok(randomQues);
    }

}