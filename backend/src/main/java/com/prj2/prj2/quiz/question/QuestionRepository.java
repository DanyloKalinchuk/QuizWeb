package com.prj2.prj2.quiz.question;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    public List<Question> findByQuiz_IdOrderByQuestionNumberAsc(Long quizId);
}
