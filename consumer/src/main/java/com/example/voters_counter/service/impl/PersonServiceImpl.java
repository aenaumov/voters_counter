package com.example.voters_counter.service.impl;

import com.example.voters_counter.dto.Votes;
import com.example.voters_counter.entities.Person;
import com.example.voters_counter.repository.UserReactiveRepository;
import com.example.voters_counter.service.PersonService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@AllArgsConstructor
public class PersonServiceImpl implements PersonService {

    private UserReactiveRepository userReactiveRepository;
    private final KafkaTemplate<String, Votes> template;
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public Mono<Person> addVotes(Votes votes) {

        final String family = votes.getFamily().toUpperCase();
        final Long vote = votes.getVotes();

        return userReactiveRepository.findPersonByFamily(family)
                .switchIfEmpty(Mono.error(new RuntimeException("Person '" + family + "' doesn't exist")))
                .flatMap(person -> {
                    person.setVotes(person.getVotes() + vote);
                    log.info("Try add {} votes to {}", vote, family);
                    return userReactiveRepository.save(person);
                })
                .doOnError(RuntimeException.class,
                        (ex) -> template.send("topic_not_exist_votes", "not_exist_vote", votes))
                ;
    }

}
