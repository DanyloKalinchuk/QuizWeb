package com.prj2.prj2.quiz.option;

import org.springframework.stereotype.Component;

import com.prj2.prj2.quiz.question.Question;

@Component
public class AnswerOptionMapper {
    public AnswerOptionDTO toDTO(AnswerOption answerOption){
        AnswerOptionDTO answerOptionDTO = new AnswerOptionDTO();

        answerOptionDTO.setId(answerOption.getId());
        answerOptionDTO.setQuestionID(answerOption.getQuestionId());
        answerOptionDTO.setOptionContent(answerOption.getOptionContent());
        answerOptionDTO.setPoints(answerOption.getPoints());

        return answerOptionDTO;
    }

    public AnswerOption toEntity(AnswerOptionDTO answerOptionDTO, Question question){
        AnswerOption answerOption = new AnswerOption();

        answerOption.setId(answerOptionDTO.getId());
        answerOption.setQuestion(question);
        answerOption.setOptionContent(answerOptionDTO.getOptionContent());
        answerOption.setPoints(answerOptionDTO.getPoints());

        return answerOption;
    }
}
