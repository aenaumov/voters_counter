package com.example.voters_counter.service.impl;

import com.example.voters_counter.dto.Votes;
import com.example.voters_counter.service.VotesService;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class VotesServiceImpl implements VotesService {

    private final KafkaTemplate<String, Votes> template;

    @Override
    @Transactional("kafkaTransactionManager")
    public void addVotes(Votes votes){
         template.send("topic_vote", votes);
    }

}
