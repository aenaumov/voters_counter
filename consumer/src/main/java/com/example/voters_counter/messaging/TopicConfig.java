package com.example.voters_counter.messaging;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class TopicConfig {

    @Bean
    public NewTopic topic() {
        return TopicBuilder.name("topic_not_exist_votes")
                .partitions(1)
                .replicas(1)
                .build();
    }
}
