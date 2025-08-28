package com.prj2.prj2.history;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;

public class HistoryDTO {
    @NotNull
    private Long userId;

    @NotNull
    private Long quizId;

    @NotNull
    private LocalDateTime takenAt;

    public HistoryDTO() {}

    public Long getUserId(){
        return this.userId;
    }

    public void setUserId(Long userId){
        this.userId = userId;
    }

    public Long getQuizId(){
        return this.quizId;
    }

    public void setQuizId(Long quizId){
        this.quizId = quizId;
    }

    public LocalDateTime getTakenAt(){
        return this.takenAt;
    }

    public void setTakenAt(LocalDateTime takenAt){
        this.takenAt = takenAt;
    }
}
