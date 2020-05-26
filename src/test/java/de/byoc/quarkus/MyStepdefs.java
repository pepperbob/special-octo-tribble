package de.byoc.quarkus;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class MyStepdefs {

    @Inject
    MumbleService mumble;

    String result;

    @Given("Something tries to mumble")
    public void somethingTriesToMumble() {
        result = mumble.doMumble();
    }

    @Then("it will have mumbledcore")
    public void itWillHaveMumbledcore() {
        assert result != null : "No it's null!!";
    }
}
