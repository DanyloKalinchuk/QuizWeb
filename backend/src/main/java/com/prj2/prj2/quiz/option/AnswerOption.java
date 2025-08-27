package com.prj2.prj2.quiz.option;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.prj2.prj2.quiz.question.Question;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
@Table(name = "options")
public class AnswerOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    @JsonIgnore
    private Question question;

    @Length(min = 1, max = 50, message = "The length of the option text must be between 1 and 50 characters")
    @Column(name = "option_content")
    private String optionContent;

    @PositiveOrZero(message = "Points value must be positive or zero")
    @Column(name = "points", nullable = false)
    private Double points;

    public AnswerOption() {}

    public AnswerOption(Question question, String optionContent, Double points){
        this.question = question;
        this.optionContent = optionContent;
        this.points = points;
    }

    public Long getQuestionId(){
        return this.question.getId();
    }

    public void setQuestion(Question question){
        this.question = question;
    }

    public String getOptionContent(){
        return this.optionContent;
    }

    public void setOptionContent(String optionContent){
        this.optionContent = optionContent;
    }

    public Double getPoints(){
        return this.points;
    }

    public void setPoints(Double points){
        this.points = points;
    }
}
