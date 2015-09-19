package com.exercise.saletaxes;

import com.exercise.saletaxes.model.goods.ShoppingCart;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.exercise.saletaxes.model.goods.GoodBuilder.aGood;
import static com.exercise.saletaxes.model.goods.GoodType.*;

/**
 * Examples tests
 */
public class ExerciseTest {

    private Logger logger = LoggerFactory.getLogger(ExerciseTest.class);

    @Test
    public void testInput1() {
        final ShoppingCart input = new ShoppingCart(
            aGood().named("1 book").ofType(BOOKS).withFinalPrice("12.49").build(),
            aGood().named("1 music CD").ofType(OTHER).withFinalPrice("14.99").build(),
            aGood().named("1 chocolate bar").ofType(FOOD).withFinalPrice("0.85").build()
        );

        logger.info(input.print());
    }

    @Test
    public void testInput2() {
        final ShoppingCart input = new ShoppingCart(
                aGood().named("1 imported box of chocolates").ofType(FOOD).imported(true).withFinalPrice("10.00").build(),
                aGood().named("1 imported bottle of perfume").ofType(OTHER).imported(true).withFinalPrice("47.50").build()
        );

        logger.info(input.print());
    }

    @Test
    public void testInput3() {
        final ShoppingCart input = new ShoppingCart(
                aGood().named("1 imported bottle of perfume").ofType(OTHER).imported(true).withFinalPrice("27.99").build(),
                aGood().named("1 bottle of perfume").ofType(OTHER).withFinalPrice("18.99").build(),
                aGood().named("1 packet of headache pills").ofType(MEDICAL).withFinalPrice("9.75").build(),
                aGood().named("1 box of imported chocolates").ofType(FOOD).imported(true).withFinalPrice("0.85").build()
        );

        logger.info(input.print());
    }

}
