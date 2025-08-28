package com.prj2.prj2.quiz.option;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.PositiveOrZero;

public class AnswerOptionDTO {
    private Long id;
    private Long questionID;
    @Length(min = 1, max = 50, message = "The length of the option text must be between 1 and 50 characters")
    private String optionContent;
    @PositiveOrZero(message = "Points value must be positive or zero")
    private Double points;

    public AnswerOptionDTO() {}

    public AnswerOptionDTO(Long id, Long questionID, String optionContent, Double points){
        this.id = id;
        this.questionID = questionID;
        this.optionContent = optionContent;
        this.points = points;
    }

    public Long getId(){
        return this.id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public Long getQuestionID(){
        return this.questionID;
    }

    public void setQuestionID(Long questionID){
        this.questionID = questionID;
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
