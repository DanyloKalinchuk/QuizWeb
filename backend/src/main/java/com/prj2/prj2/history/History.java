package com.prj2.prj2.history;

import java.time.LocalDateTime;

import com.prj2.prj2.quiz.Quiz;
import com.prj2.prj2.user.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "history")
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "quiz_id", nullable = false)
    private Quiz quiz;

    @Column(name = "taken_at", nullable = false)
    private LocalDateTime takenAt;

    public History() {}

    public Long getUserId(){
        return this.user.getId();
    }

    public void setUser(User user){
        this.user = user;
    }

    public Long getQuizId(){
        return this.quiz.getId();
    }

    public void setQuiz(Quiz quiz){
        this.quiz = quiz;
    }

    public LocalDateTime getTakenAt(){
        return this.takenAt;
    }

    public void setTakenAt(LocalDateTime takenAt){
        this.takenAt = takenAt;
    }
}
