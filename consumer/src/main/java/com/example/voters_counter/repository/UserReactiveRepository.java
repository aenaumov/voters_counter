package com.example.voters_counter.repository;

import com.example.voters_counter.entities.Person;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Mono;

public interface UserReactiveRepository extends R2dbcRepository<Person, Long> {
        Mono<Person> findPersonByFamily(String family);
    }

