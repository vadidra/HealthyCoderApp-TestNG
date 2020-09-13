package com.healthycoderapp.testng;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class BMICalculatorTest {

    @Test
    void returnTrue_When_DietRecommended() {

        //given
        double weight = 89.0;
        double height = 1.72;

        //when
        boolean recommended = BMICalculator.isDietRecommended(weight,height);

        //then
        assertTrue(recommended);
    }

    @Test
    void returnFalse_When_DietNotRecommended() {

        //given
        double weight = 50.0;
        double height = 1.92;

        //when
        boolean recommended = BMICalculator.isDietRecommended(weight,height);

        //then
        assertFalse(recommended);
    }

    @Test
    void throwArithmeticException_When_HeightZero() {

        //given
        double weight = 50.0;
        double height = 0.0;

        //when
        //lambda expression
       ThrowingRunnable runnabale = () -> BMICalculator.isDietRecommended(weight,height);

        //then
        assertThrows(runnabale);

    }


}