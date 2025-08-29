package com.prj2.prj2.quiz.option;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerOptionRepository extends JpaRepository<AnswerOption, Long> {
    public List<AnswerOption> findByQuestion_Id(Long questionId);
}
