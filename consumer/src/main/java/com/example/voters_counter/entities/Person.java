package com.example.voters_counter.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Builder
@Table(name = "PERSONS")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
public class Person {

    @Id
    @Column(name = "PERSON_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "PERSON_NAME")
    private String name;
    @Column(name = "FAMILY")
    private String family;

    @Column(name = "VOTES")
    private Long votes;

    @Column(name = "CREATED", updatable = false, nullable = false)
    @CreationTimestamp
    private LocalDateTime created;

    @Column(name = "UPDATED", nullable = false)
    @UpdateTimestamp
    private LocalDateTime updated;

}
