package com.prj2.prj2.quiz.question;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.prj2.prj2.quiz.Quiz;
import com.prj2.prj2.quiz.option.AnswerOption;
import com.prj2.prj2.quiz.option.AnswerOptionDTO;
import com.prj2.prj2.quiz.option.AnswerOptionMapper;

@Component
public class QuestionMapper {
    @Autowired
    private AnswerOptionMapper answerOptionMapper;

    public QuestionDTO toDTO(Question question){
        QuestionDTO questionDTO = new QuestionDTO();

        questionDTO.setQuestionId(question.getId());
        questionDTO.setQuestionContent(question.getQuestionContent());
        questionDTO.setQuestionNumber(question.getQuestionNumber());
        questionDTO.setQuizId(question.getQuizId());

        for (AnswerOption answerOption : question.getAnswerOptions()){
            AnswerOptionDTO answerOptionDTO = answerOptionMapper.toDTO(answerOption);
            questionDTO.addAnswerOption(answerOptionDTO);
        }

        return questionDTO;
    }

    public Question toEntity(QuestionDTO questionDTO, Quiz quiz){
        Question question = new Question();

        question.setId(questionDTO.getQuestionId());
        question.setQuiz(quiz);
        question.setQuestionContent(questionDTO.getQuestionContent());
        question.setQuestionNumber(questionDTO.getQuestionNumber());

        for (AnswerOptionDTO answerOptionDTO : questionDTO.getAnswerOptions()){
            AnswerOption answerOption = answerOptionMapper.toEntity(answerOptionDTO, question);
            question.addAnswerOption(answerOption);
        }

        return question;
    }

}
