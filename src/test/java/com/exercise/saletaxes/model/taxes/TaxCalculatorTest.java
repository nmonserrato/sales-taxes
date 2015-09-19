package com.exercise.saletaxes.model.taxes;

import com.exercise.saletaxes.model.goods.Good;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static com.exercise.saletaxes.model.goods.GoodBuilder.aGood;
import static com.exercise.saletaxes.model.goods.GoodType.*;
import static java.math.BigDecimal.ZERO;
import static org.junit.Assert.assertEquals;

/**
 * Test class for SwissTaxCalculator
 */
public class TaxCalculatorTest {

    private SwissTaxCalculator calculator;

    @Before
    public void initCalculator() {
        calculator = new SwissTaxCalculator();
    }

    @Test
    public void calculateTaxes_priceZero_taxesZero() {
        final Good freeItem = aGood().named("cannolo sample").ofType(FOOD).withFinalPrice("0.00").build();
        final BigDecimal taxesAmount = calculator.calculateTaxes(freeItem);
        assertEquals(0, taxesAmount.compareTo(ZERO));
    }

    @Test
    public void calculateTaxes_lessThan5CentsItem_zeroTaxes() {
        final Good bag = aGood().named("plastic bag").ofType(OTHER).withFinalPrice("0.05").build();
        final BigDecimal taxesAmount = calculator.calculateTaxes(bag);
        assertEquals(0, taxesAmount.compareTo(ZERO));
    }

    @Test
    public void calculateTaxes_reallyCheapItem_minimum5CentsTaxes() {
        final Good candy = aGood().named("candy").imported(true).ofType(FOOD).withFinalPrice("0.10").build();
        final BigDecimal taxesAmount = calculator.calculateTaxes(candy);
        assertEquals(0, taxesAmount.compareTo(new BigDecimal("0.05")));
    }

    @Test
    public void calculateTaxes_nonImportedItem_10PercentTaxes() {
        final Good perfume = aGood().named("simple perfume").ofType(OTHER).withFinalPrice("14.99").build();
        final BigDecimal taxesAmount = calculator.calculateTaxes(perfume);
        assertEquals(0, taxesAmount.compareTo(new BigDecimal("1.50")));
    }

    @Test
    public void calculateTaxes_importedFood_5PercentTaxes() {
        final Good perfume = aGood().named("imported chocolate").imported(true).ofType(FOOD).withFinalPrice("20").build();
        final BigDecimal taxesAmount = calculator.calculateTaxes(perfume);
        assertEquals(0, taxesAmount.compareTo(new BigDecimal("1.00")));
    }

    @Test
    public void calculateTaxes_nonImportedMedical_0PercentTaxes() {
        final Good perfume = aGood().named("pain killers").imported(false).ofType(MEDICAL).withFinalPrice("13.50").build();
        final BigDecimal taxesAmount = calculator.calculateTaxes(perfume);
        assertEquals(0, taxesAmount.compareTo(ZERO));
    }

    @Test
    public void calculateTaxes_importedSmartphone_15PercentTaxes() {
        final Good perfume = aGood().named("meizu mx5").imported(true).ofType(OTHER).withFinalPrice("200.0").build();
        final BigDecimal taxesAmount = calculator.calculateTaxes(perfume);
        assertEquals(0, taxesAmount.compareTo(new BigDecimal("30")));
    }

    @Test
    public void roundTo5Cents_zeroOrFive_zeroOrFive() {
        assertEquals(new BigDecimal("6.45"), calculator.roundTo5Cents(new BigDecimal("6.45")));
        assertEquals(new BigDecimal("12.00"), calculator.roundTo5Cents(new BigDecimal("12.0000")));
    }

    @Test
    public void roundTo5Cents_justOverZero_roundToFive() {
        assertEquals(new BigDecimal("7.05"), calculator.roundTo5Cents(new BigDecimal("7.0000001")));
        assertEquals(new BigDecimal("142.15"), calculator.roundTo5Cents(new BigDecimal("142.11")));
    }

    @Test
    public void roundTo5Cents_justOverFive_roundToZero() {
        assertEquals(new BigDecimal("1932.60"), calculator.roundTo5Cents(new BigDecimal("1932.5500001")));
        assertEquals(new BigDecimal("0.80"), calculator.roundTo5Cents(new BigDecimal("0.77")));
    }
}
