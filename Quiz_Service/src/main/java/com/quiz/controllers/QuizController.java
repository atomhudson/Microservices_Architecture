package com.quiz.controllers;


import com.quiz.entities.Quiz;
import com.quiz.serivces.QuizService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {
    private QuizService quizService;
    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }
    @PostMapping("/create")
    public Quiz create (@RequestBody Quiz quiz) {
        return quizService.add(quiz);
    }
    @GetMapping("/get/{quizId}")
    public Quiz get (@PathVariable long quizId) {
        return quizService.get(quizId);
    }
    @GetMapping("/getAll")
    public List<Quiz> getAll(){
        return quizService.getAll();
    }
}
