package com.prj2.prj2.quiz;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.prj2.prj2.quiz.question.QuestionDTO;
import com.prj2.prj2.quiz.question.QuestionMapper;
import com.prj2.prj2.quiz.question.QuestionRepository;
import com.prj2.prj2.user.UserRepository;

@Component
public class QuizMapper {
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private QuestionMapper questionMapper;

    public QuizDTO toDTO(Quiz quiz){
        List<QuestionDTO> questions = questionMapper.listToDTO(questionRepository.findByQuiz_IdOrderByQuestionNumberAsc(quiz.getId()));
        QuizDTO quizDTO = new QuizDTO(quiz.getId(), quiz.getCreatorId(), quiz.getName(), quiz.getType(), questions);
        return quizDTO;
    }

    public Quiz toEntity(QuizDTO quizDTO){
        Quiz quiz = new Quiz(quizDTO.getQuizId(), userRepository.findById(quizDTO.getCreatorId()).orElseThrow(), quizDTO.getName(), quizDTO.getType());
        return quiz;
    }

    public List<QuizDTO> listToDTO(List<Quiz> quizzes){
        List<QuizDTO> quizzesDTO = new ArrayList<QuizDTO>();
        for (Quiz quiz : quizzes){
            quizzesDTO.add(toDTO(quiz));
        }
        return quizzesDTO;
    }
}
