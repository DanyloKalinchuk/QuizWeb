package com.prj2.prj2.quiz;

import java.util.List;

import com.prj2.prj2.quiz.question.QuestionDTO;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class QuizDTO {
    private Long quizId;
    private Long creatorId;
    private String name;
    @Enumerated(EnumType.STRING)
    private Quiz.QzType type;
    private List<QuestionDTO> questions;

    public QuizDTO() {}

    public QuizDTO(Long quizId, Long creatorId, String name, Quiz.QzType type, List<QuestionDTO> questions){
        this.quizId = quizId;
        this.creatorId = creatorId;
        this.name = name;
        this.type = type;
        this.questions = questions;
    }

    public Long getQuizId(){
        return this.quizId;
    }

    public void setQuizId(Long quizId){
        this.quizId = quizId;
    }

    public Long getCreatorId(){
        return this.creatorId;
    }

    public void setCreatorId(Long creatorId){
        this.creatorId = creatorId;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public Quiz.QzType getType(){
        return this.type;
    }

    public void setType(Quiz.QzType type){
        this.type = type;
    }

    public List<QuestionDTO> getQuestions(){
        return this.questions;
    }

    public void addQuestion(QuestionDTO questionDTO){
        this.questions.add(questionDTO);
    }

    public void setQuestions(List<QuestionDTO> questions){
        this.questions = questions;
    }
}
