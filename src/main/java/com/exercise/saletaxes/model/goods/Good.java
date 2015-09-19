package com.exercise.saletaxes.model.goods;

import java.math.BigDecimal;

/**
 * Represents a single purchased item
 */
public class Good {
    private String description;
    private GoodType category;
    private boolean imported;
    private BigDecimal finalPrice;

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
}
