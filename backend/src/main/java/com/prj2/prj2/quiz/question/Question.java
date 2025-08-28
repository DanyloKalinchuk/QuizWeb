package com.prj2.prj2.quiz.question;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.prj2.prj2.quiz.Quiz;
import com.prj2.prj2.quiz.option.AnswerOption;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "questions")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "quiz_id", nullable = false)
    private Quiz quiz;

    @Length(min = 1, max = 400, message = "Question Content must contain between 1 and 400 characters")
    @Column(name = "question_content")
    private String questionContent;

    @Column(name = "question_number", nullable = false)
    private Integer questionNumber;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<AnswerOption> answerOptions;

    public Question() {}

    public Question(Long id, Quiz quiz, String questionContent, Integer questionNumber){
        this.id = id;
        this.quiz = quiz;
        this.questionContent = questionContent;
        this.questionNumber = questionNumber;
    }

    public Long getId(){
        return this.id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public Long getQuizId(){
        return this.quiz.getId();
    }

    public void setQuiz(Quiz quiz){
        this.quiz = quiz;
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

    public void addAnswerOption(AnswerOption answerOption){
        this.answerOptions.add(answerOption);
    }

    public void setAnswerOptions(List<AnswerOption> answerOptions){
        this.answerOptions = answerOptions;
    }
}
