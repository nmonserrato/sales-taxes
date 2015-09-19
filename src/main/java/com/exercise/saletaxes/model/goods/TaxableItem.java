package com.exercise.saletaxes.model.goods;

import java.math.BigDecimal;

/**
 * Contains the behaviors shared by all taxable items
 */
public interface TaxableItem {
    BigDecimal getFinalPrice();
    BigDecimal getTaxes();
    String print();
}
