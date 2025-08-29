package com.prj2.prj2.comment;

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

import jakarta.validation.Valid;

@RestController
@RequestMapping("/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping
    public List<CommentDTO> getComments(
        @RequestParam(required = false) Long userId,
        @RequestParam(required = false) Long quizId
    ){
        if (userId != null){
            return commentService.getByUserID(userId);
        }else if (quizId != null){
            return commentService.getByQuizID(quizId);
        }else{
            return commentService.getAll();
        }
    }

    @PostMapping
    public ResponseEntity<CommentDTO> addComment(@Valid @RequestBody CommentDTO commentDTO){
        CommentDTO addedCommentDTO = commentService.addComment(commentDTO);

        if (addedCommentDTO != null){
            return new ResponseEntity<>(addedCommentDTO, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping
    public ResponseEntity<CommentDTO> updateComment(@Valid @RequestBody CommentDTO commentDTO){
        CommentDTO updatedCommentDTO = commentService.updateComment(commentDTO);

        if (updatedCommentDTO != null){
            return new ResponseEntity<>(updatedCommentDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable Long id){
        Long returnedId = commentService.deleteComment(id);

        if (returnedId != null){
            return new ResponseEntity<>("Comment deleted Successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Comment Not Found", HttpStatus.NOT_FOUND);
    }
}
