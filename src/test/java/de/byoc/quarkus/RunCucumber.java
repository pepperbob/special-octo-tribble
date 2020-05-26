package de.byoc.quarkus;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(strict = true, objectFactory = MyObjectFactory.class)
public class RunCucumber {
}
