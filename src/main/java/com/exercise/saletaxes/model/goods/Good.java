package com.exercise.saletaxes.model.goods;

import com.exercise.saletaxes.model.taxes.TaxCalculator;

import java.math.BigDecimal;

/**
 * Represents a single purchased item
 */
public class Good implements TaxableItem{
    private String description;
    private GoodType category;
    private boolean imported;
    private BigDecimal finalPrice;

    private TaxCalculator taxCalculator = TaxCalculator.getInstance();

    Good(String description, GoodType category, boolean imported, BigDecimal finalPrice) {
        this.description = description;
        this.category = category;
        this.imported = imported;
        this.finalPrice = finalPrice;
    }

    public String getDescription() {
        return description;
    }

    public GoodType getCategory() {
        return category;
    }

    public boolean isImported() {
        return imported;
    }

    public BigDecimal getFinalPrice() {
        return finalPrice;
    }

    public BigDecimal getTaxes() {
        return taxCalculator.calculateTaxes(this);
    }

    public String print() {
        return description + ": " + finalPrice;
    }
}
