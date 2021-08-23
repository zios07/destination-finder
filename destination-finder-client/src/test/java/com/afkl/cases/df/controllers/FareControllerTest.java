package com.afkl.cases.df.controllers;

import com.afkl.cases.df.models.Fare;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest
@AutoConfigureWebTestClient(timeout = "36000")
public class FareControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void testFareEndpoint() throws JsonProcessingException {

        Fare fare = objectMapper.readValue(FARE_REQUEST_BODY, Fare.class);

        webTestClient.get()
                .uri("/fares/ABV/ABV")
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody()
                .jsonPath("$.origin.code").isEqualTo(fare.getOrigin())
                .jsonPath("$.destination.code").isEqualTo(fare.getDestination());
    }


    private static final String FARE_REQUEST_BODY = "{\n" +
            "    \"amount\": 3451.05,\n" +
            "    \"currency\": \"EUR\",\n" +
            "    \"origin\": \"ABV\",\n" +
            "    \"destination\": \"ABV\"\n" +
            "}";

}
