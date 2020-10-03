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
    public void test_with_one_or_two_or_three_number() {
        assertThat(this.calculator.add("")).isEqualTo("0");
        assertThat(this.calculator.add("1,1")).isEqualTo("2");
        assertThat(this.calculator.add("1,1,3")).isEqualTo("5");
    }

    @Test
    public void test_with_unknown_numbers() {
        assertThat(this.calculator.add("1,1,1,1,1,1")).isEqualTo("6");
    }

    @Test
    public void test_with_newline_as_separator() {
        assertThat(this.calculator.add("1\n1")).isEqualTo("2");
    }

    @Test
    public void test() {
        System.out.println(this.calculator.add("1,1,"));
    }

}
