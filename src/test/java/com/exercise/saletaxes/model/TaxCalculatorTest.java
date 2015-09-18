package com.exercise.saletaxes.model;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by nino on 18/09/15.
 */
public class TaxCalculatorTest {
    @Test
    public void calculateTaxes_priceZero_taxesZero() {
        final TaxCalculator calculator = new TaxCalculator();
        float taxesAmount = calculator.calculateTaxes(0f);
        Assert.assertEquals(0f, taxesAmount, 0);
    }
}
