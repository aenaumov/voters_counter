package com.example.voters_counter.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
//@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Votes {

    @JsonProperty("family")
    private String family;

    @JsonProperty("votes")
    private Long votes;

    public Votes(String family, Long votes) {
        this.family = family.toUpperCase();
        this.votes = votes;
    }
}
