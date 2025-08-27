package com.prj2.prj2.quiz.question;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.prj2.prj2.quiz.QuizRepository;
import com.prj2.prj2.quiz.option.AnswerOption;
import com.prj2.prj2.quiz.option.AnswerOptionRepository;

@Component
public class QuestionMapper {
    @Autowired
    private AnswerOptionRepository answerOptionRepository;
    @Autowired
    private QuizRepository quizRepository;

    public QuestionDTO toDTO(Question question){
        List<AnswerOption> answerOptions = answerOptionRepository.findByQuestion_Id(question.getId());
        QuestionDTO questionDTO = new QuestionDTO(question.getId(), question.getQuestionContent(), question.getQuestionNumber(), answerOptions);
        return questionDTO;
    }

    public Question toEntity(QuestionDTO questionDTO){
        Question question = new Question(questionDTO.getQuestionId(), quizRepository.findById(questionDTO.getQuizID()).orElseThrow(),
        questionDTO.getQuestionContent(), questionDTO.getQuestionNumber());
        return question;
    }

    public List<QuestionDTO> listToDTO(List<Question> questions){
        List<QuestionDTO> questionsDTO = new ArrayList<QuestionDTO>();
        for (Question question : questions){
            questionsDTO.add(toDTO(question));
        }
        return questionsDTO;
    }

    public List<Question> listToEntity(List<QuestionDTO> questionsDTO){
        List<Question> questions = new ArrayList<Question>();
        for (QuestionDTO questionDTO : questionsDTO){
            questions.add(toEntity(questionDTO));
        }
        return questions;
    }
}
