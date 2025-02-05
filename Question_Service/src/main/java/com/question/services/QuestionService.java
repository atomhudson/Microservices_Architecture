package com.question.services;

import com.question.entities.Question;

import java.util.List;

public interface QuestionService {
    Question create(Question question);
    List<Question> getAll();
    Question get(Long id);
    List<Question> getQuestionsOfQuiz(Long quizId);
}
