package com.prj2.prj2.quiz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/quizzes")
public class QuizController {
    @Autowired
    private QuizService quizService;

    @GetMapping("/{id}")
    public QuizDTO getById(@PathVariable Long id){
        QuizDTO quizDTO = quizService.getById(id);
        if (quizDTO != null){
            return quizDTO;
        }
        return null;
    }

    @GetMapping
    public List<QuizDTO> getQuizzes(
        @RequestParam(required = false) Long creatorId,
        @RequestParam(required = false) String name
    ){
        if (creatorId != null){
            return quizService.getByCreatorId(creatorId);
        }else if (name != null){
            return quizService.getByName(name);
        }else{
            return quizService.getAll();
        }
    }

    @PostMapping
    public ResponseEntity<QuizDTO> addQuiz(@RequestBody QuizDTO quizDTO){
        QuizDTO createdQuizDTO = quizService.addQuiz(quizDTO);
        return new ResponseEntity<>(createdQuizDTO, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<QuizDTO> updateQuiz(@RequestBody QuizDTO quizDTO){
        QuizDTO updatedQuizDTO = quizService.updateQuiz(quizDTO);

        if (updatedQuizDTO != null){
            return new ResponseEntity<>(updatedQuizDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteQuiz(@PathVariable Long id){
        Long returnedId = quizService.deleteQuiz(id);

        if (returnedId != null){
            return new ResponseEntity<>("Quiz deleted Successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Quiz Not Found", HttpStatus.BAD_REQUEST);
    }
}
