package com.example.voters_counter.entities;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@Table("PERSONS")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
public class Person {

    @Id
    @Column("PERSON_ID")
    private Long id;

    @Column("PERSON_NAME")
    private String name;
    @Column("FAMILY")
    private String family;

    @Column("VOTES")
    private Long votes;

    @Column("CREATED")
    @CreatedDate
    private LocalDateTime created;

    @Version
    @Column("VERSION")
    private Long version;

}
