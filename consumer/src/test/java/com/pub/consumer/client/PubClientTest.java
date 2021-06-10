package com.pub.consumer.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerPort;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.http.MediaType;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@AutoConfigureStubRunner(ids = {
        "com.pub:provider"}, stubsMode = StubRunnerProperties.StubsMode.LOCAL)
class PubClientTest {

    @StubRunnerPort("provider")
    int producerPort;

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void should_give_me_a_beer_when_im_old_enough() {
        var adult = new ClientDto().setAge(21);
        CheckDto response = new RestTemplateBuilder()
                .defaultHeader("contentType", MediaType.APPLICATION_JSON.toString())
                .build()
                .postForObject("http://localhost:" + this.producerPort + "/clients/1/check",
                        adult, CheckDto.class);

        assertThat(response.isAdult()).isEqualTo(true);
    }

    @Test
    public void should_reject_a_beer_when_im_too_young() {
        var teenager = new ClientDto().setAge(15);
        CheckDto actualResult = null;
        try {
            new RestTemplate()
                    .postForObject("http://localhost:" + this.producerPort + "/clients/1/check",
                            teenager, CheckDto.class);
        } catch (HttpClientErrorException clientErrorException) {
            actualResult = jsonToClass(clientErrorException.getResponseBodyAsString(), CheckDto.class);
        }

        assertThat(actualResult.isAdult()).isEqualTo(false);
    }

    <T> T jsonToClass(final String json, Class<T> clazz) {
        try {
            return mapper.readValue(json, clazz);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}