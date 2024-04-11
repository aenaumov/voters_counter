package com.example.voters_counter.service;

import com.example.voters_counter.dto.Votes;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class VotesService {

    private final KafkaTemplate<String, Votes> template;

    public void addVotes(Votes votes){
         template.send("topic_vote", votes);
    }

}
