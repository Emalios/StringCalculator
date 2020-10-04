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

    @Test
    public void test_invalid_newLine(){
        assertThat(this.calculator.add("172,\n35")).containsIgnoringCase("Number expected but '\\n' found at position");
    }

    @Test
    public void test_invalid_EOF(){
        assertThat(this.calculator.add("1,3,")).containsIgnoringCase("Number expected but EOF found.");
    }

    @Test
    public void test_negative(){
        assertThat(this.calculator.add("2,-4,-5")).containsIgnoringCase("Negative not allowed : -4, -5");
    }

    @Test
    public void test_multiple_error(){
        assertThat(this.calculator.add("-1,,2"))
                .isEqualToIgnoringCase("Negative not allowed : -1\nNumber expected but ',' found at position 3");
    }

    @Test
    public void test_custome_separator(){
        assertThat(this.calculator.add("//;\n1;2")).isEqualToIgnoringCase("3");
        assertThat(this.calculator.add("//sep\n2sep3")).isEqualToIgnoringCase("5");
        assertThat(this.calculator.add("//|\n1|2|3")).isEqualToIgnoringCase("6");
    }

}
