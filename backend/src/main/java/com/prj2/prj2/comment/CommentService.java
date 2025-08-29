package com.prj2.prj2.comment;

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
public class CommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private QuizRepository quizRepository;

    public List<CommentDTO> getAll(){
        List<CommentDTO> commentsDTO = new ArrayList<CommentDTO>();

        for(Comment comment : commentRepository.findAllByOrderByPostedAtDesc()){
            commentsDTO.add(commentMapper.toDTO(comment));
        }

        return commentsDTO;
    }

    public List<CommentDTO> getByUserID(Long userId){
        List<CommentDTO> commentsDTO = new ArrayList<CommentDTO>();

        for(Comment comment : commentRepository.findByUser_Id(userId)){
            commentsDTO.add(commentMapper.toDTO(comment));
        }

        return commentsDTO;
    }

    public List<CommentDTO> getByQuizID(Long quizId){
        List<CommentDTO> commentsDTO = new ArrayList<CommentDTO>();

        for(Comment comment : commentRepository.findByQuiz_Id(quizId)){
            commentsDTO.add(commentMapper.toDTO(comment));
        }

        return commentsDTO;
    }

    public CommentDTO addComment(CommentDTO commentDTO){
        Optional<User> user = userRepository.findById(commentDTO.getUserId());
        Optional<Quiz> quiz = quizRepository.findById(commentDTO.getQuizId());

        if (user.isPresent() && quiz.isPresent()){
            Comment comment = commentMapper.toEntity(commentDTO, user.get(), quiz.get());
            return commentMapper.toDTO(commentRepository.save(comment));
        }
        return null;
    }

    public CommentDTO updateComment(CommentDTO commentDTO){
        Optional<User> user = userRepository.findById(commentDTO.getUserId());
        Optional<Quiz> quiz = quizRepository.findById(commentDTO.getQuizId());
        Optional<Comment> comment = commentRepository.findById(commentDTO.getId());

        if (user.isPresent() && quiz.isPresent() && comment.isPresent()){
            Comment updatedComment = commentMapper.toEntity(commentDTO, user.get(), quiz.get());
            return commentMapper.toDTO(commentRepository.save(updatedComment));
        }
        return null;
    }

    @Transactional
    public Long deleteComment(Long id){
        Optional<Comment> comment = commentRepository.findById(id);

        if (comment.isPresent()){
            commentRepository.deleteById(id);
            return id;
        }
        return null;
    }
}
