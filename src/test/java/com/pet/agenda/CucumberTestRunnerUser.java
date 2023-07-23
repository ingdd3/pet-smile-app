package com.pet.agenda;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/ingreso_usuario.feature",
        glue = "com.pet.agenda.steps")
public class CucumberTestRunnerUser {
}
