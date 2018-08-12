package com.learn.pact.test;

import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactProviderRuleMk2;
import au.com.dius.pact.consumer.PactRule;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.DslPart;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.RequestResponsePact;
import org.apache.http.HttpResponse;
import org.assertj.core.api.Assertions;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class PactTest {

    private DslPart helloWorldResults;

    @Rule
    public PactProviderRuleMk2 rule = new PactProviderRuleMk2("provider",
            "localhost",
            9080,
            this);

    @Pact(consumer = "consumer")
    public RequestResponsePact buildPact(final PactDslWithProvider pactbuilder) {

        helloWorldResults = new PactDslJsonBody()
                .id()
                .stringType("content")
                .asBody();

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
                .body(helloWorldResults)
                .toPact();
    }

    @Test
    @PactVerification
    public void testProducerPact() throws IOException {
        Consumer consumer = new Consumer("http://localhost:9080");
        assertEquals(200, consumer.getResponseStatus());
    }
}
