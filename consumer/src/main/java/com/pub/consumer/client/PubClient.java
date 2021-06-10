package com.pub.consumer.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pub.consumer.ClientDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
public class PubClient {

    private final RestTemplate restTemplate;
    private final ObjectMapper mapper;
    private final String baseUrl;

    public boolean canClientOrderBear(ClientDto client) {
        CheckDto checkResult;
        try {
            checkResult = new RestTemplate()
                    .postForObject(baseUrl + "/clients/1/check",
                            client, CheckDto.class);
        } catch (HttpClientErrorException clientErrorException) {
            checkResult = jsonToClass(clientErrorException.getResponseBodyAsString(), CheckDto.class);
        }
        return checkResult.isAdult();
    }


    private <T> T jsonToClass(final String json, Class<T> clazz) {
        try {
            return mapper.readValue(json, clazz);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
