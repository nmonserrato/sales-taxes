package com.exercise.saletaxes.model.goods;

/**
 * Available categories of purchasable items with the information if it's subject to taxes
 */
public enum GoodType {
    BOOKS(false), FOOD(false), MEDICAL(false), OTHER(true);

    private boolean salesTaxable;

    GoodType(boolean salesTaxable) {
        this.salesTaxable = salesTaxable;
    }

    public boolean isSalesTaxable() {
        return salesTaxable;
    }
}
