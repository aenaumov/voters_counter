package com.example.voters_counter.service;

import com.example.voters_counter.dto.Votes;
import com.example.voters_counter.entities.Person;
import reactor.core.publisher.Mono;

public interface PersonService {
    Mono<Person> addVotes(Votes votes);
}
