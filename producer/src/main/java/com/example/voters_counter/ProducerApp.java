package com.example.voters_counter;

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

    @Bean
    public NewTopic topic() {
        return TopicBuilder.name("topic1")
                .partitions(10)
                .replicas(1)
                .build();
    }

    @Bean
    public ApplicationRunner runner(KafkaTemplate<String, String> template) {

        return args -> {
            for (int i=0; i < 1000; i++){
                template.send("topic1", String.valueOf(i), "test");
            }
        };
    }

    }
