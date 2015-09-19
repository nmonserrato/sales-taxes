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
    private BigDecimal price;

    private TaxCalculator taxCalculator = TaxCalculator.getInstance();

    Good(String description, GoodType category, boolean imported, BigDecimal price) {
        this.description = description;
        this.category = category;
        this.imported = imported;
        this.price = price;
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

    public BigDecimal getPrice() {
        return price;
    }

    public BigDecimal getFinalPrice() {
        return this.price.add(this.getTaxes());
    }

    public BigDecimal getTaxes() {
        return taxCalculator.calculateTaxes(this);
    }

    public String print() {
        return description + ": " + getFinalPrice();
    }
}
