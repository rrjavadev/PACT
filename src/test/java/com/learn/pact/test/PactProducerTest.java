package com.learn.pact.test;

import au.com.dius.pact.provider.junit.PactRunner;
import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.State;
import au.com.dius.pact.provider.junit.loader.PactFolder;
import au.com.dius.pact.provider.junit.target.HttpTarget;
import au.com.dius.pact.provider.junit.target.Target;
import au.com.dius.pact.provider.junit.target.TestTarget;
import com.learn.pact.producer.PactProducer;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringApplication;
import org.springframework.web.context.ConfigurableWebApplicationContext;

@RunWith(PactRunner.class)
@Provider("test-provider")
@PactFolder("pacts")
public class PactProducerTest {
    @TestTarget
    public final Target target = new HttpTarget(8080);

    private static ConfigurableWebApplicationContext applicationContext;

    public void setUp() {
        applicationContext = (ConfigurableWebApplicationContext)
                SpringApplication.run(PactProducer.class);
    }

    @State("test GET")
    public void toGetState() {
    }
}
