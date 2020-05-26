package de.byoc.quarkus;

import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@ExtendWith(PactConsumerTestExt.class)
@QuarkusTest
public class ExampleResourcePactTest {

    @Pact(provider = "provider", consumer = "consumer")
    public RequestResponsePact pact(PactDslWithProvider builder) {
        return builder.given("Something")
                .uponReceiving("Otherthing")
                .method("GET")
                .path("/")
                .willRespondWith()
                .body("hello")
                .toPact();
    }

    @Test
    public void testHelloEndpoint() {
        given()
                .when().get("/hello")
                .then()
                .statusCode(200)
                .body(is("hello"));
    }

}