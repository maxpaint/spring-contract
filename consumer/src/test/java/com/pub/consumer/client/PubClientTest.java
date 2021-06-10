package com.pub.consumer.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pub.consumer.ClientDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerPort;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
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

    private RestTemplate restTemplate = new RestTemplate();

    private static PubClient pubClient;

    @BeforeEach
    public void init() {
        pubClient = new PubClient(restTemplate, mapper, "http://localhost:" + this.producerPort);
    }


    @Test
    public void should_give_me_a_beer_when_im_old_enough() {
        var adult = new ClientDto().setAge(21);
        boolean actualResult = pubClient.canClientOrderBear(adult);
        assertThat(actualResult).isEqualTo(true);
    }

    @Test
    public void should_reject_a_beer_when_im_too_young() {
        var teenager = new ClientDto().setAge(15);
        boolean actualResult = pubClient.canClientOrderBear(teenager);
        assertThat(actualResult).isEqualTo(false);
    }

    /*@Test
    public void should_reject_a_beer_when_im_too_old() {
        var teenager = new ClientDto().setAge(80);
        boolean actualResult = pubClient.canClientOrderBear(teenager);
        assertThat(actualResult).isEqualTo(false);
    }*/
}