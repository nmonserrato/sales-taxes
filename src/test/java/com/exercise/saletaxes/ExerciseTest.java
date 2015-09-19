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
            aGood().named("1 book").ofType(BOOKS).withPrice("12.49").build(),
            aGood().named("1 music CD").ofType(OTHER).withPrice("14.99").build(),
            aGood().named("1 chocolate bar").ofType(FOOD).withPrice("0.85").build()
        );

        logger.info(input.print());
    }

    @Test
    public void testInput2() {
        final ShoppingCart input = new ShoppingCart(
                aGood().named("1 imported box of chocolates").ofType(FOOD).imported(true).withPrice("10.00").build(),
                aGood().named("1 imported bottle of perfume").ofType(OTHER).imported(true).withPrice("47.50").build()
        );

        logger.info(input.print());
    }

    @Test
    public void testInput3() {
        final ShoppingCart input = new ShoppingCart(
                aGood().named("1 imported bottle of perfume").ofType(OTHER).imported(true).withPrice("27.99").build(),
                aGood().named("1 bottle of perfume").ofType(OTHER).withPrice("18.99").build(),
                aGood().named("1 packet of headache pills").ofType(MEDICAL).withPrice("9.75").build(),
                aGood().named("1 box of imported chocolates").ofType(FOOD).imported(true).withPrice("11.25").build()
        );

        logger.info(input.print());
    }

}
