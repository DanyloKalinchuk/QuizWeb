package com.prj2.prj2.history;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/history")
public class HistoryController {
    @Autowired
    private HistoryService historyService;

    @GetMapping
    public List<HistoryDTO> get(
        @RequestParam(required = false) Long userId,
        @RequestParam(required = false) Long quizId
    ){
        if (userId != null){
            return historyService.getByUserId(userId);
        }else if (quizId != null){
            return historyService.getByQuizId(quizId);
        }else{
            return historyService.getAll();
        }
    }

    @PostMapping
    public ResponseEntity<HistoryDTO> add(@Valid @RequestBody HistoryDTO historyDTO){
        HistoryDTO addeHistoryDTO = historyService.addHistory(historyDTO);
        if (addeHistoryDTO != null){
            return new ResponseEntity<>(addeHistoryDTO, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
