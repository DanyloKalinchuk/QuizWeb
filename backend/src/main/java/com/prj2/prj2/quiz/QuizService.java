package com.prj2.prj2.quiz;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.prj2.prj2.quiz.option.AnswerOption;
import com.prj2.prj2.quiz.option.AnswerOptionRepository;
import com.prj2.prj2.quiz.question.Question;
import com.prj2.prj2.quiz.question.QuestionDTO;
import com.prj2.prj2.quiz.question.QuestionMapper;
import com.prj2.prj2.quiz.question.QuestionRepository;

import jakarta.transaction.Transactional;

@Component
public class QuizService {
    @Autowired
    private QuizRepository quizRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private AnswerOptionRepository answerOptionRepository;
    @Autowired
    private QuizMapper quizMapper;
    @Autowired
    private QuestionMapper questionMapper;

    public List<QuizDTO> getAll(){
        return quizMapper.listToDTO(quizRepository.findAll());
    }

    public QuizDTO getById(Long id){
        Optional<Quiz> existingQuiz = quizRepository.findById(id);
        if (existingQuiz.isPresent()){
            return quizMapper.toDTO(existingQuiz.get());
        }
        return null;
    }

    public List<QuizDTO> getByCreatorId(Long creatorId){
        return quizMapper.listToDTO(quizRepository.findByCreator_Id(creatorId));
    }

    public List<QuizDTO> getByName(String name){
        return quizMapper.listToDTO(quizRepository.findByNameContainingIgnoreCase(name));
    }

    public QuizDTO addQuiz(QuizDTO quizDTO){
        quizRepository.save(quizMapper.toEntity(quizDTO));
        
        for (QuestionDTO questionDTO : quizDTO.getQuestions()){
            questionRepository.save(questionMapper.toEntity(questionDTO));
            for (AnswerOption answerOption : questionDTO.getAnswerOptions()){
                answerOptionRepository.save(answerOption);
            }
        }

        return quizDTO;
    }

    public QuizDTO updateQuiz(QuizDTO quizDTO){
        Optional<Quiz> existingQuiz = quizRepository.findById(quizDTO.getQuizId());
        
        if (existingQuiz.isPresent()){
            for (QuestionDTO questionDTO : quizDTO.getQuestions()){
                questionRepository.save(questionMapper.toEntity(questionDTO));
                for (AnswerOption answerOption : questionDTO.getAnswerOptions()){
                    answerOptionRepository.save(answerOption);
                }
            }

            return quizMapper.toDTO(quizRepository.save(quizMapper.toEntity(quizDTO)));
        }
        return null;
    }

    @Transactional
    public Long deleteQuiz(Long quizId){
        Optional<Quiz> quiz = quizRepository.findById(quizId);

        if (quiz.isPresent()){
            List<Question> questions = questionRepository.findByQuiz_IdOrderByQuestionNumberAsc(quizId);
            for (Question question : questions){
                answerOptionRepository.deleteAllByQuestion_Id(question.getId());
            }
            questionRepository.deledeleteAllByQuiz_Id(quizId);
            quizRepository.deleteById(quizId);
            return quizId;
        }
        return null;
    }
}
