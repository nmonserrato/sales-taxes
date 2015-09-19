package com.exercise.saletaxes.model;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

import static java.math.BigDecimal.ZERO;
import static java.math.BigDecimal.valueOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Test class for TaxCalculator
 */
public class TaxCalculatorTest {

    private TaxCalculator calculator;
    private Logger logger = LoggerFactory.getLogger(TaxCalculatorTest.class);

    @Before
    public void initCalculator() {
        calculator = new TaxCalculator();
    }

    @Test
    public void calculateTaxes_priceZero_taxesZero() {
        final BigDecimal taxesAmount = calculator.calculateTaxes(new BigDecimal(0));
        assertEquals(0, taxesAmount.compareTo(ZERO));
    }

    @Test
    public void calculateTaxes_negativePrice_exception() {
        try {
            calculator.calculateTaxes(valueOf(-12.3));
            fail();
        } catch (NumberFormatException nfe) {
            logger.info("NumberFormatException received!", nfe);
        }
    }

    @Test
    public void calculateTaxes_reallyCheapItem_minimum5CentsTaxes() {
        final BigDecimal taxesAmount = calculator.calculateTaxes(new BigDecimal("0.01"));
        assertEquals(0, taxesAmount.compareTo(new BigDecimal("0.05")));
    }

    @Test
    public void calculateTaxes_normalItem_10PercentTaxes() {
        final BigDecimal taxesAmount = calculator.calculateTaxes(new BigDecimal("14.99"));
        assertEquals(0, taxesAmount.compareTo(new BigDecimal("1.50")));
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
