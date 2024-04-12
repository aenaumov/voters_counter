package com.example.voters_counter.dto;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Votes{

    private String family;

    private Long votes;
}
