package com.example.voters_counter;

import com.example.voters_counter.dto.Votes;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication
public class ProducerApp {
    public static void main(String[] args) {
        SpringApplication.run(ProducerApp.class, args);
    }
    final ObjectMapper objectMapper = new ObjectMapper();

    @Bean
    public NewTopic topic() {
        return TopicBuilder.name("topic_vote")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public ApplicationRunner runner(KafkaTemplate<String, String> template) throws JsonProcessingException {

        final Votes votes = Votes.builder()
                .family("PUTIN")
                .votes(1L)
                .build();
        final String jsonVotes = objectMapper.writeValueAsString(votes);

        return args -> {
            for (int i = 0; i < 21; i++) {
                template.send("topic_vote", jsonVotes);
            }
        };
    }

}