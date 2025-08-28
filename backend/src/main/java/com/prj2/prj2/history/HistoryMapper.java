package com.prj2.prj2.history;

import org.springframework.stereotype.Component;

import com.prj2.prj2.quiz.Quiz;
import com.prj2.prj2.user.User;

@Component
public class HistoryMapper {
    public HistoryDTO toDTO(History history){
        HistoryDTO historyDTO = new HistoryDTO();

        historyDTO.setUserId(history.getUserId());
        historyDTO.setQuizId(history.getQuizId());
        historyDTO.setTakenAt(history.getTakenAt());

        return historyDTO;
    }

    public History toEntity(HistoryDTO historyDTO, User user, Quiz quiz){
        History history = new History();

        history.setUser(user);
        history.setQuiz(quiz);
        history.setTakenAt(historyDTO.getTakenAt());

        return history;
    }
}
