package com.exercise.saletaxes.model;

import java.math.BigDecimal;

import static java.math.BigDecimal.ROUND_UP;
import static java.math.BigDecimal.valueOf;

/**
 * Class for actual taxes computation
 */
public class TaxCalculator {
    final BigDecimal scale = new BigDecimal("0.05");

    public BigDecimal calculateTaxes(BigDecimal itemPrice) {
        if (itemPrice.signum() < 0) {
            throw new NumberFormatException("Item price is negative: " + itemPrice);
        }
        return roundTo5Cents(itemPrice.multiply(valueOf(10)).divide(valueOf(100)));
    }

    public BigDecimal roundTo5Cents(BigDecimal price) {
        return price.divide(scale).setScale(0, ROUND_UP).multiply(scale);
    }
}
