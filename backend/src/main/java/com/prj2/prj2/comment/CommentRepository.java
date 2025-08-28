package com.prj2.prj2.comment;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    public List<Comment> findByUser_Id(Long userId);
    public List<Comment> findByQuiz_Id(Long quizId);

    public List<Comment> findAllByOrderByPostedAtDesc();
}
