package com.prj2.prj2.quiz;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long>{
    public List<Quiz> findByCreator_Id(Long creatorId);
    public List<Quiz> findByNameContainingIgnoreCase(String name);
}
