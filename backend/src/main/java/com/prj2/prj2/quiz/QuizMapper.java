package com.prj2.prj2.quiz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.prj2.prj2.quiz.question.Question;
import com.prj2.prj2.quiz.question.QuestionDTO;
import com.prj2.prj2.quiz.question.QuestionMapper;
import com.prj2.prj2.user.User;

@Component
public class QuizMapper {
    @Autowired
    private QuestionMapper questionMapper;

    public QuizDTO toDTO(Quiz quiz){
        QuizDTO quizDTO = new QuizDTO();

        quizDTO.setQuizId(quiz.getId());
        quizDTO.setCreatorId(quiz.getCreatorId());
        quizDTO.setName(quiz.getName());
        quizDTO.setType(quiz.getType());

        for (Question question : quiz.getQuestions()){
            QuestionDTO questionDTO = questionMapper.toDTO(question);
            quizDTO.addQuestion(questionDTO);
        }

        return quizDTO;
    }

    public Quiz toEntity(QuizDTO quizDTO, User user){
        Quiz quiz = new Quiz();

        quiz.setId(quizDTO.getQuizId());
        quiz.setCreator(user);
        quiz.setName(quizDTO.getName());
        quiz.setType(quizDTO.getType());

        for(QuestionDTO questionDTO : quizDTO.getQuestions()){
            Question question = questionMapper.toEntity(questionDTO, quiz);
            quiz.addQuestion(question);
        }

        return quiz;
    }
}
