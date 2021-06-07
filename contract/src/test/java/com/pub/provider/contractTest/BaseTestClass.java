package com.pub.provider.contractTest;

import com.pub.provider.client.api.rest.ClientController;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeAll;

public class BaseTestClass {

    @BeforeAll
    public static void setup() {
        RestAssuredMockMvc.standaloneSetup(new ClientController());
    }
}
