package com.learn.pact.test;

import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactProviderRuleMk2;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.RequestResponsePact;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpStatus.OK;

public class PactConsumerTest {

    @Rule
    public PactProviderRuleMk2 rule = new PactProviderRuleMk2("test-provider",
            "localhost",
            9080,
            this);

    @Pact(consumer = "test-consumer")
    public RequestResponsePact buildPact(final PactDslWithProvider pactbuilder) {

        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");

        return pactbuilder.
                given("test GET")
                .uponReceiving("GET REQUEST")
                .path("/hello-world")
                .method("GET")
                .willRespondWith()
                .status(200)
                .headers(headers)
                .body(new PactDslJsonBody()
                        .id()
                        .stringType("content")
                        .asBody())
                .toPact();
    }

    @Test
    @PactVerification
    public void testConsumerPact() {
        // when
        ResponseEntity<String> response = new RestTemplate()
                .getForEntity(rule.getUrl() + "/hello-world", String.class);
        assertThat(response.getStatusCode()).isEqualTo(OK);
    }
}
