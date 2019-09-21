package com.anand.stringcalculator;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StringCalculatorTest {


    private StringCalculator stringCalculator;

    @Before
    public void setUp() {
        stringCalculator = new StringCalculator();
    }

    @Test
    public void should_return_zero_for_empty_string() {
        assertThat(stringCalculator.add("")).isEqualTo(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_IllegalArgumentException_if_passed_string_is_not_a_number() {
        stringCalculator.add("1,s,3");
    }


    @Test
    public void should_return_the_same_number_when_input_is_only_one_number() {
        assertThat(stringCalculator.add("3")).isEqualTo(3);
    }

    @Test
    public void should_return_sum_of_every_amount_of_numbers_when_delimiter_is_only_comma() {
        assertThat(stringCalculator.add("1,2")).isEqualTo(3);
        assertThat(stringCalculator.add("3,2,5")).isEqualTo(10);
    }

    @Test
    public void should_return_sum_of_every_amount_of_numbers_when_delimiter_is_only_new_line() {
        assertThat(stringCalculator.add("1\n2")).isEqualTo(3);
        assertThat(stringCalculator.add("3\n2\n6")).isEqualTo(11);
    }

    @Test(expected = NumberFormatException.class)
    public void should_throw_NumberFormatException_when_delimiter_is_not_separated_by_newline() {
        stringCalculator.add("//;3;2;4");
    }


    @Test
    public void should_return_sum_when_single_custom_delimiter_is_specified() {
        assertThat(stringCalculator.add("//[;]\n3;2;1")).isEqualTo(6);
        assertThat(stringCalculator.add("//[*]\n4*3*2")).isEqualTo(9);
    }

    @Test
    public void should_return_sum_when_single_type_and_any_length_custom_delimiter_is_passed() {
        assertThat(stringCalculator.add("//[ddd]\n2ddd3ddd5")).isEqualTo(10);
        assertThat(stringCalculator.add("//[***]\n1***2***4")).isEqualTo(7);
    }

    @Test
    public void should_return_sum_when_multiple_type_and_any_length_custom_delimiters_are_specified() {
        assertThat(stringCalculator.add("//[***][%]\n2***3%6")).isEqualTo(11);
    }
}
