package com.exercise.saletaxes.model.goods;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

import static com.exercise.saletaxes.model.goods.GoodBuilder.aGood;
import static com.exercise.saletaxes.model.goods.GoodType.BOOKS;
import static com.exercise.saletaxes.model.goods.GoodType.FOOD;
import static org.junit.Assert.*;

/**
 * Test class for GoodBuilder
 */
public class GoodBuilderTest {
    private Logger logger = LoggerFactory.getLogger(GoodBuilderTest.class);

    @Test
    public void build_categoryNotSet_exception() {
        try {
            aGood().named("example").imported(true).withFinalPrice("10").build();
            fail();
        } catch (IllegalArgumentException iae) {
            logger.error("IllegalArgumentException received", iae);
        }
    }

    @Test
    public void build_priceNotSet_exception() {
        try {
            aGood().named("example").imported(true).ofType(FOOD).build();
            fail();
        } catch (IllegalArgumentException iae) {
            logger.error("IllegalArgumentException received", iae);
        }
    }

    @Test
    public void build_invalidPriceSet_exception() {
        try {
            aGood().named("example").imported(true).ofType(FOOD).withFinalPrice("ciao").build();
            fail();
        } catch (IllegalArgumentException iae) {
            logger.error("IllegalArgumentException received", iae);
        }
    }

    @Test
    public void build_negativePriceSet_exception() {
        try {
            aGood().named("example").imported(true).ofType(FOOD).withFinalPrice("-1.4").build();
            fail();
        } catch (IllegalArgumentException iae) {
            logger.error("IllegalArgumentException received", iae);
        }
    }

    @Test
    public void build_importNotSpecified_defaultFalse() {
        final Good good = aGood().named("example").ofType(FOOD).withFinalPrice("10").build();
        assertFalse(good.isImported());
    }

    @Test
    public void build_descriptionNotSpecified_defaultEmpty() {
        final Good good = aGood().ofType(BOOKS).withFinalPrice("10").build();
        assertEquals("", good.getDescription());
    }

    @Test
    public void build_allFieldsSet_sameFieldsOnItem() {
        final Good good = aGood().named("chocolate box").ofType(FOOD).withFinalPrice("13.98").imported(true).build();

        assertEquals("chocolate box", good.getDescription());
        assertEquals(FOOD, good.getCategory());
        assertTrue(good.isImported());
        assertEquals(0, new BigDecimal("13.98").compareTo(good.getFinalPrice()));
    }
}