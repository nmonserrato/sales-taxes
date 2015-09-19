package com.exercise.saletaxes.model.goods;

import org.junit.Test;

import java.math.BigDecimal;

import static com.exercise.saletaxes.model.goods.GoodBuilder.aGood;
import static com.exercise.saletaxes.model.goods.GoodType.*;
import static java.math.BigDecimal.ZERO;
import static org.junit.Assert.assertEquals;

/**
 * ShoppingCart test class
 */
public class ShoppingCartTest {

    @Test
    public void getFinalPrice_noItems_getZero() {
        final ShoppingCart cart = new ShoppingCart();
        assertEquals(0, ZERO.compareTo(cart.getFinalPrice()));
    }

    @Test
    public void getFinalPrice_oneItem_getThatItem() {
        final ShoppingCart cart = new ShoppingCart(
                aGood().ofType(FOOD).withFinalPrice("12.98").build()
        );
        assertEquals(0, new BigDecimal("12.98").compareTo(cart.getFinalPrice()));
    }

    @Test
    public void getFinalPrice_multipleItems_getItemsSum() {
        final ShoppingCart cart = new ShoppingCart(
                aGood().ofType(FOOD).withFinalPrice("12.98").build(),
                aGood().ofType(MEDICAL).withFinalPrice("5.99").build(),
                aGood().ofType(OTHER).withFinalPrice("199.00").build()
        );
        assertEquals(0, new BigDecimal("217.97").compareTo(cart.getFinalPrice()));
    }

    @Test
    public void getTaxes_noItems_getZero() {
        final ShoppingCart cart = new ShoppingCart();
        assertEquals(0, ZERO.compareTo(cart.getTaxes()));
    }

    @Test
    public void getTaxes_oneItem_getThatItemTaxes() {
        final ShoppingCart cart = new ShoppingCart(
                aGood().ofType(OTHER).withFinalPrice("7.48").build()
        );
        assertEquals(0, new BigDecimal("0.75").compareTo(cart.getTaxes()));
    }

    @Test
    public void getTaxes_multipleItems_getItemsTaxesSumT() {
        final ShoppingCart cart = new ShoppingCart(
                aGood().named("item1").ofType(BOOKS).withFinalPrice("10").build(),
                aGood().named("item2").ofType(MEDICAL).imported(true).withFinalPrice("20").build(),
                aGood().named("item3").ofType(OTHER).withFinalPrice("25.90").build(),
                aGood().named("item4").ofType(OTHER).imported(true).withFinalPrice("30.50").build()
        );
        assertEquals(0, new BigDecimal("8.20").compareTo(cart.getTaxes()));
    }

}