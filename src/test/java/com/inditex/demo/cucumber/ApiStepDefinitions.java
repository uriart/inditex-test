package com.inditex.demo.cucumber;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ApiStepDefinitions {

    @Autowired
    private TestRestTemplate restTemplate;

    private ResponseEntity<String> response;
    private String endpoint;

    @Given("I send a GET request to {string}")
    public void i_send_a_get_request_to(String endpoint) {
        this.endpoint = endpoint;
        response = restTemplate.getForEntity(endpoint, String.class);
    }

    @Then("the response status should be {int}")
    public void the_response_status_should_be(int expectedStatus) {
        assertEquals(expectedStatus, response.getStatusCodeValue());
    }

    @Then("the response body should contain {string}")
    public void the_response_body_should_contain(String expectedContent) {
        assertTrue(response.getBody().contains(expectedContent));
    }
}
