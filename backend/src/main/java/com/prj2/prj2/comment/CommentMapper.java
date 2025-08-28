package com.prj2.prj2.comment;

import org.springframework.stereotype.Component;

import com.prj2.prj2.quiz.Quiz;
import com.prj2.prj2.user.User;

@Component
public class CommentMapper {
    public CommentDTO toDTO(Comment comment){
        CommentDTO commentDTO = new CommentDTO();

        commentDTO.setId(comment.getId());
        commentDTO.setUserId(comment.getUserId());
        commentDTO.setQuizId(comment.getQuizId());
        commentDTO.setContent(comment.getContent());
        commentDTO.setPostedAt(comment.getPostedAt());

        return commentDTO;
    }

    public Comment toEntity(CommentDTO commentDTO, User user, Quiz quiz){
        Comment comment = new Comment();

        comment.setId(commentDTO.getId());
        comment.setUser(user);
        comment.setQuiz(quiz);
        comment.setContent(commentDTO.getContent());
        commentDTO.setPostedAt(commentDTO.getPostedAt());

        return comment;
    }
}
