package com.exercise.saletaxes.model.goods;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import static java.lang.System.lineSeparator;
import static java.util.Arrays.asList;

/**
 * Represents a list of purchased goods
 */
public class ShoppingCart implements TaxableItem, Iterable<TaxableItem> {

    private Collection<TaxableItem> goods;

    public ShoppingCart(TaxableItem... items) {
        if (items == null || items.length == 0) {
            goods = new ArrayList<TaxableItem>();
        } else {
            goods = asList(items);
        }
    }

    public BigDecimal getFinalPrice() {
        BigDecimal cartPrice = BigDecimal.ZERO;
        for (TaxableItem item : this) {
            cartPrice = cartPrice.add(item.getFinalPrice());
        }
        return cartPrice;
    }

    public BigDecimal getTaxes() {
        BigDecimal totalTaxes = BigDecimal.ZERO;
        for (TaxableItem item : this) {
            totalTaxes = totalTaxes.add(item.getTaxes());
        }
        return totalTaxes;
    }

    public String print() {
        final StringBuilder sb = new StringBuilder();
        for (TaxableItem cartItem : this) {
            sb.append(cartItem.print()).append(lineSeparator());
        }
        sb.append("Sales taxes: ").append(getTaxes()).append(lineSeparator());
        sb.append("Total: ").append(getFinalPrice());
        return sb.toString();
    }


    public Iterator<TaxableItem> iterator() {
        return goods.iterator();
    }
}
