package com.prj2.prj2.comment;

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
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "quiz_id", nullable = false)
    private Quiz quiz;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "posted_at", nullable = false)
    private LocalDateTime postedAt;

    public Comment() {}

    public Long getId(){
        return this.id;
    }

    public void setId(Long id){
        this.id = id;
    }

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

    public String getContent(){
        return this.content;
    }

    public void setContent(String content){
        this.content = content;
    }

    public LocalDateTime getPostedAt(){
        return this.postedAt;
    }

    public void setPostedAt(LocalDateTime postedAt){
        this.postedAt = postedAt;
    }
}
