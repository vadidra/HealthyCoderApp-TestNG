package com.healthycoderapp.testng;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static org.testng.Assert.*;

public class DietPlannerTest {

    private DietPlanner dietPlanner;

    @BeforeTest
    void setUp(){
        this.dietPlanner = new DietPlanner(20, 30, 50);
    }

    @AfterTest
    void afterEach(){
        System.out.println("A unit test was finished");
    }

    @Test
    public void returnCorrectDietPlan_When_CorrectCoder() {

        // given
        Coder coder = new Coder(1.82, 75.0, 26, Gender.MALE);
        DietPlan expected = new DietPlan(2202, 110, 73, 275);

        // when
        DietPlan actual = dietPlanner.calculateDiet(coder);

        // then
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(expected.getCalories(), actual.getCalories());
        softAssert.assertEquals(expected.getProtein(), actual.getProtein());
        softAssert.assertEquals(expected.getFat(), actual.getFat());
        softAssert.assertEquals(expected.getCarbohydrate(), actual.getCarbohydrate());
        softAssert.assertAll();

    }
}