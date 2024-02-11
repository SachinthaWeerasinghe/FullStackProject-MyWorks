/**
 * Developed By S.A. WEERASINGHE - PS/2019/259
 *  * Quiz Page Development
 */

package com.example.quiz.service;

import com.example.quiz.model.Quiz;
import com.example.quiz.repository.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;



@Service
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService {
    private final QuizRepository quizRepo;
    @Override
    public Quiz createQuiz(Quiz q) {
        return quizRepo.save(q);
    }

    @Override
    public List<Quiz> getAllQuestions() {
        return quizRepo.findAll();
    }

    @Override
    public Optional<Quiz> getQuestionById(Long id) {
        return quizRepo.findById(id);
    }

    @Override
    public List<String> getAllSubjects() {
        return quizRepo.findSpecifiedSubject();
    }

    @Override
    public Quiz updateQuiz(Long id, Quiz question) throws ChangeSetPersister.NotFoundException {
        Optional<Quiz> ques = this.getQuestionById(id);
        if (ques.isPresent()){
            Quiz updatedQuiz = ques.get();
            updatedQuiz.setQuestion(question.getQuestion());
            updatedQuiz.setMultiple_options(question.getMultiple_options());
            updatedQuiz.setCorrect_option(question.getCorrect_option());
            return quizRepo.save(updatedQuiz);
        }else {
            throw new ChangeSetPersister.NotFoundException();
        }

    }
    @Override
    public void deleteQuestion(Long id) {
        quizRepo.deleteById(id);
    }
    @Override
    public List<Quiz> getQuizForStudent(Integer numOfQuestions, String subject) {
        Pageable pageable = PageRequest.of(0, numOfQuestions);
        return quizRepo.findBySubject(subject, pageable).getContent();
    }


}