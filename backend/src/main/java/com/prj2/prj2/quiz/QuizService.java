package com.prj2.prj2.quiz;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.prj2.prj2.user.User;
import com.prj2.prj2.user.UserRepository;

import jakarta.transaction.Transactional;

@Component
public class QuizService {
    @Autowired
    private QuizRepository quizRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private QuizMapper quizMapper;

    public List<QuizDTO> getAll(){
        List<QuizDTO> quizzesDTO = new ArrayList<QuizDTO>();
        
        for (Quiz quiz : quizRepository.findAll()){
            quizzesDTO.add(quizMapper.toDTO(quiz));
        }

        return quizzesDTO;
    }

    public QuizDTO getById(Long id){
        Optional<Quiz> existingQuiz = quizRepository.findById(id);

        if (existingQuiz.isPresent()){
            return quizMapper.toDTO(existingQuiz.get());
        }
        return null;
    }

    public List<QuizDTO> getByCreatorId(Long creatorId){
        List<QuizDTO> quizzesDTO = new ArrayList<QuizDTO>();

        for (Quiz quiz : quizRepository.findByCreator_Id(creatorId)){
            quizzesDTO.add(quizMapper.toDTO(quiz));
        }
        return quizzesDTO;
    }

    public List<QuizDTO> getByName(String name){
        List<QuizDTO> quizzesDTO = new ArrayList<QuizDTO>();

        for (Quiz quiz : quizRepository.findByNameContainingIgnoreCase(name)){
            quizzesDTO.add(quizMapper.toDTO(quiz));
        }
        return quizzesDTO;
    }

    public QuizDTO addQuiz(QuizDTO quizDTO){
        Optional<User> existingUser = userRepository.findById(quizDTO.getCreatorId());

        if (existingUser.isPresent()){
            Quiz quiz = quizMapper.toEntity(quizDTO, existingUser.get());
            quizRepository.save(quiz);
            return quizDTO;
        }
        return null;
    }

    public QuizDTO updateQuiz(QuizDTO quizDTO){
        Optional<Quiz> existingQuiz = quizRepository.findById(quizDTO.getQuizId());
        Optional<User> existingUser = userRepository.findById(quizDTO.getCreatorId());
        
        if (existingQuiz.isPresent() && existingUser.isPresent()){
            Quiz quiz = quizMapper.toEntity(quizDTO, existingUser.get());

            return quizMapper.toDTO(quizRepository.save(quiz));
        }
        return null;
    }

    @Transactional
    public Long deleteQuiz(Long quizId){
        Optional<Quiz> existingQuiz = quizRepository.findById(quizId);

        if (existingQuiz.isPresent()){
            quizRepository.deleteById(quizId);
            return quizId;
        }
        return null;
    }
}
