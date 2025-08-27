package com.prj2.prj2.quiz;

import java.util.List;

import com.prj2.prj2.quiz.question.QuestionDTO;

public class QuizDTO {
    private Long quizId;
    private Long creatorId;
    private String name;
    private String type;
    private List<QuestionDTO> questions;

    public QuizDTO() {}

    public QuizDTO(Long quizId, Long creatorId, String name, String type, List<QuestionDTO> questions){
        this.quizId = quizId;
        this.creatorId = creatorId;
        this.name = name;
        this.type = type;
        this.questions = questions;
    }

    public QuizDTO(Long quizId, Long creatorId, String name, Quiz.QzType type, List<QuestionDTO> questions){
        this.quizId = quizId;
        this.creatorId = creatorId;
        this.name = name;
        this.questions = questions;

        if (type.equals(Quiz.QzType.SINGLE_OPTN)){
            this.type = "SINGLE_OPTN";
        }else if (type.equals(Quiz.QzType.MULTIPLE_OPTN)){
            this.type = "MULTIPLE_OPTN";
        }
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

    public String getType(){
        return this.type;
    }

    public void setType(Quiz.QzType type){
        if (type.equals(Quiz.QzType.SINGLE_OPTN)){
            this.type = "SINGLE_OPTN";
        }else if (type.equals(Quiz.QzType.MULTIPLE_OPTN)){
            this.type = "MULTIPLE_OPTN";
        }
    }

    public List<QuestionDTO> getQuestions(){
        return this.questions;
    }

    public void setQuestions(List<QuestionDTO> questions){
        this.questions = questions;
    }
}
