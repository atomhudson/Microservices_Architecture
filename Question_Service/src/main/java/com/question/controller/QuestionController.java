package com.question.controller;

import com.question.entities.Question;
import com.question.services.QuestionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {
    private QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping("/create")
    public Question create (@RequestBody Question question) {
        return questionService.create(question);
    }

    @GetMapping("/getAll")
    public List<Question> getAllQuestions() {
        return questionService.getAll();
    }

    @GetMapping("/get/{questionId}")
    public Question getQuestionById(@PathVariable Long questionId) {
        return questionService.get(questionId);
    }

    @GetMapping("/quiz/{quizId}")
    public List<Question> getQuestionsOfQuiz(@PathVariable Long quizId) {
        return questionService.getQuestionsOfQuiz(quizId);
    }
}
