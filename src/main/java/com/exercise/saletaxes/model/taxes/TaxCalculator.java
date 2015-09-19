package com.exercise.saletaxes.model.taxes;

import com.exercise.saletaxes.model.goods.Good;

import java.math.BigDecimal;

/**
 * Interface for a tax calculator
 */
public abstract class TaxCalculator {
    public abstract BigDecimal calculateTaxes(Good good);

    /* factory method per tax calculator */
    public static TaxCalculator getInstance() {
        return new SwissTaxCalculator();
    }
}
