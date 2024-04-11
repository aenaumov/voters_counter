package com.example.voters_counter.messaging;


import com.example.voters_counter.dto.Votes;
import com.example.voters_counter.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Component
public class VotesConsumer {
    @Autowired
    private PersonService personService;

    @KafkaListener(topics = "topic_vote", batch = "true")
    public void processMessage(List<Votes> content) {

        content.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.groupingBy(Votes::getFamily,
                        Collectors.reducing(0L, Votes::getVotes, Long::sum)))
                .entrySet()
                .parallelStream()
                .map(e -> new Votes(e.getKey(), e.getValue()))
                .peek(v -> log.info("Batch votes {} to {}", v.getVotes(), v.getFamily()))
                .forEach(v -> personService.addVotes(v).subscribe());
    }

}
