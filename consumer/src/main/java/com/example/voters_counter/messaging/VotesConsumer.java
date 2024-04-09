package com.example.voters_counter.messaging;


import com.example.voters_counter.dto.Votes;
import com.example.voters_counter.service.PersonService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Slf4j
@Component
public class VotesConsumer {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private PersonService personService;

    @KafkaListener(topics = "topic_vote", batch = "true")
    public void processMessage(List<String> content) {

        List<Votes> votes = content.stream()
                .filter(Objects::nonNull)
                .map(j -> {
                    try {
                        return objectMapper.readValue(j, Votes.class);
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.groupingBy(Votes::getFamily,
                        Collectors.reducing(0L, Votes::getVotes, Long::sum)))
                .entrySet()
                .stream()
                .map(e -> new Votes(e.getKey(), e.getValue()))
                .peek(v -> log.info("Batch votes {} to {}", v.getVotes(), v.getFamily()))
                .toList();


        CompletableFuture[] futures =
                votes.stream()
                .map(vote -> CompletableFuture.runAsync(() -> personService.addVotes(vote).subscribe()))
                .toArray(CompletableFuture[]::new);
        CompletableFuture.allOf(futures).join();


    }

}
