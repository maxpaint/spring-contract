package com.pub.producer.client.api.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ClientController.class)
class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    void check_clientAge21_returnIsAdult_true() throws Exception {
        //GIVEN:
        var adult = new ClientDto().setAge(21);

        //WHEN:
        // Execute the POST request
        mockMvc.perform(post("/clients/1/check")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(asJsonString(adult)))
               //@formatter:off
       //THEN:
               //@formatter:on
               .andExpect(status().isOk())
               .andExpect(jsonPath("adult", is(true)));
    }

    @Test
    void check_clientAge17_returnIsAdult_true() throws Exception {
        //GIVEN:
        var adult = new ClientDto().setAge(17);

        //WHEN:
        // Execute the POST request
        mockMvc.perform(post("/clients/1/check")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(asJsonString(adult)))
               //@formatter:off
       //THEN:
               //@formatter:on
               .andExpect(status().isForbidden())
               .andExpect(jsonPath("adult", is(false)));
    }

    String asJsonString(final Object obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}