package com.prj2.prj2.history;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryRepository extends JpaRepository<History, Long> {
    public List<History> findByUser_Id(Long userId);
    public List<History> findByQuiz_Id(Long quizId);
    public List<History> findAllByOrderByTakenAtDesc();
}
