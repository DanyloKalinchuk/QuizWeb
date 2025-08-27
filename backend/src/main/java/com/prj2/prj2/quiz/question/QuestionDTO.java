package com.prj2.prj2.quiz.question;

import java.util.List;

import com.prj2.prj2.quiz.option.AnswerOption;

public class QuestionDTO {
    private Long questionId;
    private String questionContent;
    private Integer questionNumber;
    private List<AnswerOption> answerOptions;
    private Long quizId;

    public QuestionDTO() {}

    public QuestionDTO(Long questionId, String questionContent, Integer questionNumber, List<AnswerOption> answerOptions){
        this.questionId = questionId;
        this.questionContent = questionContent;
        this.questionNumber = questionNumber;
        this.answerOptions = answerOptions;
    }

    public Long getQuestionId(){
        return this.questionId;
    }

    public void setQuestionId(Long questionId){
        this.questionId = questionId;
    }

    public String getQuestionContent(){
        return this.questionContent;
    }

    public void setQuestionContent(String questionContent){
        this.questionContent = questionContent;
    }

    public Integer getQuestionNumber(){
        return this.questionNumber;
    }

    public void setQuestionNumber(Integer questionNumber){
        this.questionNumber = questionNumber;
    }

    public List<AnswerOption> getAnswerOptions(){
        return this.answerOptions;
    }

    public void setAnswerOptions(List<AnswerOption> answerOptions){
        this.answerOptions = answerOptions;
    }

    public Long getQuizID(){
        return this.quizId;
    }

    public void setQuizId(Long quizId){
        this.quizId = quizId;
    }
}
