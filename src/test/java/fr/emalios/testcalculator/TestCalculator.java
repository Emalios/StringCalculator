package fr.emalios.testcalculator;

import fr.emalios.calculator.Calculator;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class TestCalculator {

    private Calculator calculator;

    @Before
    public void before() {
        this.calculator = new Calculator();
    }

    @Test
    public void test_with_one_string() {
        assertThat(this.calculator.add("1, 1")).isEqualTo("2");
    }

}
