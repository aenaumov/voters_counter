package com.example.voters_counter.repository;

import com.example.voters_counter.entities.Person;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.relational.core.sql.LockMode;
import org.springframework.data.relational.repository.Lock;
import org.springframework.data.repository.query.Param;
import reactor.core.publisher.Mono;

public interface UserReactiveRepository extends R2dbcRepository<Person, Long> {
    @Lock(LockMode.PESSIMISTIC_WRITE)
    Mono<Person> findPersonByFamily(@Param("family") String family);

}

