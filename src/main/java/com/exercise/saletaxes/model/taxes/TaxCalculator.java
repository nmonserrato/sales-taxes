package com.exercise.saletaxes.model.taxes;

import com.exercise.saletaxes.model.goods.Good;

import java.math.BigDecimal;

/**
 * Interface for a tax calculator
 */
public abstract class TaxCalculator {
    public abstract BigDecimal calculateTaxes(Good good);

    private static final TaxCalculator INSTANCE = new SwissTaxCalculator();

    /* factory method per tax calculator */
    public static TaxCalculator getInstance() {
        return INSTANCE;
    }
}
