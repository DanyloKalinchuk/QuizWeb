package com.prj2.prj2.quiz;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.prj2.prj2.quiz.question.Question;
import com.prj2.prj2.user.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "quizzes")
public class Quiz {
    static public enum QzType{
        SINGLE_OPTN,
        MULTIPLE_OPTN
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "creator_id", nullable = false)
    private User creator;

    @Length(min = 3, max = 100, message = "The length of a quiz name must be between 3 and 100 characters")
    @NotBlank(message = "Quiz name must contain charecters")
    @Column(name = "name", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private Quiz.QzType type;

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Question> questions;

    public Quiz() {}

    public Quiz(Long id, User creator, String name, Quiz.QzType type){
        this.id = id;
        this.creator = creator;
        this.name = name;
        this.type = type;
    }

    public Quiz(Long id, User creator, String name, String type){
        this.id = id;
        this.creator = creator;
        this.name = name;
        
        if (type == "SINGLE_OPTN"){
            this.type = Quiz.QzType.SINGLE_OPTN;
        }else if (type == "MULTIPLE_OPTN"){
            this.type = Quiz.QzType.MULTIPLE_OPTN;
        }
    }

    public Long getId(){
        return this.id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public Long getCreatorId(){
        return this.creator.getId();
    }

    public void setCreator(User creator){
        this.creator = creator;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public Quiz.QzType getType(){
        return this.type;
    }

    public void setType(Quiz.QzType type){
        this.type = type;
    }
}
