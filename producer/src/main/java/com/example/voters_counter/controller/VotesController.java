package com.example.voters_counter.controller;

import com.example.voters_counter.dto.Votes;
import com.example.voters_counter.service.VotesService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
public class VotesController {

    private final VotesService votesService;

    @PostMapping("/add-votes")
    public ResponseEntity<String> addVote(@RequestBody Votes votes){

        try {
            votesService.addVotes(votes);
            log.info("Vote's been added");
            return new ResponseEntity<>("Your vote has been added", HttpStatus.ACCEPTED);
        } catch (Exception ex){
            log.warn("Something gone wrong: ", ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
