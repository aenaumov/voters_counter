package com.example.voters_counter.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column("PERSON_NAME")
    private String name;
    @Column("FAMILY")
    private String family;

    @Column("VOTES")
    private Long votes;

    @Column("CREATED")
    @CreationTimestamp
    private LocalDateTime created;

    @Version
    @Column( "UPDATED")
    @UpdateTimestamp
    private LocalDateTime updated;

}
