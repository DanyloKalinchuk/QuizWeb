package com.prj2.prj2.history;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.prj2.prj2.quiz.Quiz;
import com.prj2.prj2.quiz.QuizRepository;
import com.prj2.prj2.user.User;
import com.prj2.prj2.user.UserRepository;

import jakarta.transaction.Transactional;

@Component
public class HistoryService {
    @Autowired
    private HistoryMapper historyMapper;
    @Autowired
    private HistoryRepository historyRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private QuizRepository quizRepository;

    public List<HistoryDTO> getAll(){
        List<HistoryDTO> historyDTOs = new ArrayList<HistoryDTO>();

        for (History history : historyRepository.findAllByOrderByTakenAtDesc()){
            historyDTOs.add(historyMapper.toDTO(history));
        }
        return historyDTOs;
    }

    public List<HistoryDTO> getByUserId(Long userId){
        List<HistoryDTO> historyDTOs = new ArrayList<HistoryDTO>();

        for (History history : historyRepository.findByUser_Id(userId)){
            historyDTOs.add(historyMapper.toDTO(history));
        }
        return historyDTOs;
    }

    public List<HistoryDTO> getByQuizId(Long quizId){
        List<HistoryDTO> historyDTOs = new ArrayList<HistoryDTO>();

        for (History history : historyRepository.findByQuiz_Id(quizId)){
            historyDTOs.add(historyMapper.toDTO(history));
        }
        return historyDTOs;
    }

    @Transactional
    public HistoryDTO addHistory(HistoryDTO historyDTO){
        Optional<User> user = userRepository.findById(historyDTO.getUserId());
        Optional<Quiz> quiz = quizRepository.findById(historyDTO.getQuizId());

        if (user.isPresent() && quiz.isPresent()){
            History history = historyMapper.toEntity(historyDTO, user.get(), quiz.get());
            historyRepository.save(history);

            return historyDTO;
        }
        return null;
    }
}
