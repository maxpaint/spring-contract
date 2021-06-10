package com.pub.consumer.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pub.consumer.client.PubClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ClientConfiguration {

    @Bean
    PubClient getPubClient(ObjectMapper mapper) {
        return new PubClient(new RestTemplate(), mapper, "");

    }
}
