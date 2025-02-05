package com.quiz.serivces;

import com.quiz.entities.Quiz;

import java.util.List;

public interface QuizService {
    Quiz add(Quiz quiz);
    List<Quiz> getAll();
    Quiz get(Long id);
}
