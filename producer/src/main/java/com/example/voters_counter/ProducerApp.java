package com.example.voters_counter;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;

@SpringBootApplication
public class ProducerApp {
    public static void main(String[] args) {
        SpringApplication.run(ProducerApp.class, args);
    }

    @Bean
    public NewTopic topic() {
        return TopicBuilder.name("topic_vote")
                .partitions(1)
                .replicas(1)
                .build();
    }

}