package com.example.voters_counter.service.impl;

import com.example.voters_counter.dto.Votes;
import com.example.voters_counter.entities.Person;
import com.example.voters_counter.repository.UserReactiveRepository;
import com.example.voters_counter.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Slf4j
@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private UserReactiveRepository userReactiveRepository;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public Mono<Person> addVotes(Votes votes) {

        final String family = votes.getFamily().toUpperCase();
        final Long vote = votes.getVotes();

        return userReactiveRepository.findPersonByFamily(family)
                .filter(Objects::nonNull)
                .switchIfEmpty(Mono.error(new RuntimeException("Person '" + family + "' doesn't exist")))
                .flatMap(person -> {
                    person.setVotes(person.getVotes() + vote);
                    log.info("Try add {} votes to {}", vote, family);
                    return userReactiveRepository.save(person);
                }) ;
    }
}
