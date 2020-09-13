package com.healthycoderapp.testng;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;


public class BMICalculatorTest {

    @BeforeClass
    static void beforeAll(){
        System.out.println("Before all");
    }

    @AfterClass
    static void afterAll(){
        System.out.println("After all");
    }

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

    @Test(dataProvider = "provideWeightHigh")
    @Parameters("weight, height")
    void returnTrue_When_DietRecommended_Parameterized(double weight, double height) {

        //given

        //when
        boolean recommended = BMICalculator.isDietRecommended(weight,height);
        System.out.println(recommended);

        //then
        assertTrue(recommended);
    }

    @DataProvider(name = "provideWeightHigh")
    public Object[][] provideData() {

        return new Object[][] {
                { 89.0, 1.72 },
                { 86.0, 1.82 },
                { 98.0, 1.62 }
        };
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

    @Test
    void returnCoderWithWorstBMI_When_CoderListNotEmpty(){

        //given
        List<Coder> coders = new ArrayList<>();
        coders.add(new Coder(1.80, 60.0));
        coders.add(new Coder(1.82, 98.0));
        coders.add(new Coder(1.82, 64.7));

        //when
        Coder coderWorstBMI = BMICalculator.findCoderWithWorstBMI(coders);

        //then
        // multi assertion
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(1.82, coderWorstBMI.getHeight());
        softAssert.assertEquals(98.0, coderWorstBMI.getWeight());
        softAssert.assertAll();
    }

    @Test
    void returnNullWorstBMI_When_CoderListEmpty(){

        //given
        List<Coder> coders = new ArrayList<>();

        //when
        Coder coderWorstBMI = BMICalculator.findCoderWithWorstBMI(coders);

        //then
        assertNull(coderWorstBMI);
    }

    @Test
    void returnCorrectBMIScoreArray_When_CoderListNotEmpty(){

        //given
        List<Coder> coders = new ArrayList<>();
        coders.add(new Coder(1.80, 60.0));
        coders.add(new Coder(1.82, 98.0));
        coders.add(new Coder(1.82, 64.7));
        double[] expected = {18.52, 29.59, 19.53};

        //when
        double[] bmiScores = BMICalculator.getBMIScores(coders);

        //then
        assertEquals(expected, bmiScores);
    }

}