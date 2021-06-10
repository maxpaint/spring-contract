package com.pub.producer.contractTest;

import com.pub.producer.client.api.rest.ClientController;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeAll;

public class BaseTestClass {

    @BeforeAll
    public static void setup() {
        RestAssuredMockMvc.standaloneSetup(new ClientController());
    }
}
