package com.exercise.saletaxes.model.goods;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

/**
 * Utility for building a valid item
 */
public class GoodBuilder {
    private String description = "";
    private GoodType category;
    private boolean imported;
    private BigDecimal price;
    private Logger logger = LoggerFactory.getLogger(GoodBuilder.class);

    public static GoodBuilder aGood() {
        return new GoodBuilder();
    }

    public GoodBuilder named(String description) {
        this.description = description;
        return this;
    }

    public GoodBuilder ofType(GoodType category) {
        this.category = category;
        return this;
    }

    public GoodBuilder imported(boolean imported) {
        this.imported = imported;
        return this;
    }

    public GoodBuilder withPrice(String price) {
        try {
            this.price = new BigDecimal(price);
        } catch (NumberFormatException  nfe) {
            logger.error("Input price [{}] is invalid, ignoring", price);
        }
        return this;
    }

    public Good build() {
        /* check mandatory fields */
        if (this.category == null || price == null) {
            throw new IllegalArgumentException("Category and price are mandatory");
        }

        /* price must not be lower than zero! */
        if (price.signum() < 0) {
            throw new IllegalArgumentException("Final price must be greater than zero!");
        }

        return new Good(description, category, imported, price);
    }
}