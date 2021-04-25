package tqs.lab5;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.platform.engine.Cucumber;

@Cucumber
public class CalculatorSteps {
    private Calculator calc;

    @Given("a calculator I just turned on")
    public void setup() {
        calc = new Calculator();
    }

    @When("I add {int} and {int}")
    public void add(int arg1, int arg2) {
        calc.push(arg1);
        calc.push(arg2);
        calc.push("+");
    }

    @When("I subtract {int} from {int}")
    public void subtract(int arg1, int arg2) {
        calc.push(arg2);
        calc.push(arg1);
        calc.push("-");
    }

    @Then("I get {int}")
    public void i_get(double expected) {
        assertEquals(expected, calc.value());
    }

}
