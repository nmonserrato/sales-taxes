package com.exercise.saletaxes.model.taxes;

import com.exercise.saletaxes.model.goods.Good;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

import static java.math.BigDecimal.*;

/**
 * Class for actual taxes computation
 */
public class SwissTaxCalculator extends TaxCalculator{
    private static final int SALES_TAXES_AMOUNT = 10;
    private static final int IMPORT_TAXES_AMOUNT = 5;

    private static final BigDecimal scale = new BigDecimal("0.05");
    private Logger logger = LoggerFactory.getLogger(SwissTaxCalculator.class);

    SwissTaxCalculator() {
    }

    public BigDecimal calculateTaxes(Good good) {
        final BigDecimal itemPrice = good.getPrice();

        /* check negative price, should never happen but this is a public API */
        if (itemPrice.signum() < 0) {
            throw new NumberFormatException("Item price is negative: " + itemPrice);
        }

        /* normal tax calculation */
        int totalTaxesPercentage = good.isImported() ? IMPORT_TAXES_AMOUNT : 0;
        totalTaxesPercentage += good.getCategory().isSalesTaxable() ? SALES_TAXES_AMOUNT : 0;
        logger.debug("Total taxes percentage for {} is {}", good.getDescription(), totalTaxesPercentage);

        return roundTo5Cents(itemPrice.multiply(valueOf(totalTaxesPercentage)).divide(valueOf(100), ROUND_HALF_UP));
    }

    public BigDecimal roundTo5Cents(BigDecimal price) {
        return price.divide(scale, ROUND_HALF_UP).setScale(0, ROUND_UP).multiply(scale);
    }
}
