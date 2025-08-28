package com.prj2.prj2.comment;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CommentDTO {
    private Long id;

    @NotNull
    private Long userId;

    @NotNull
    private Long quizId;

    @NotBlank
    private String content;

    @NotNull
    private LocalDateTime postedAt;

    public CommentDTO() {}

    public Long getId(){
        return this.id;
    }

    public void setId(Long id){
        this.id = id;
    }

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
