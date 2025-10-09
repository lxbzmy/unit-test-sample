package com.foo.currency;

import java.math.BigDecimal;
import java.util.Currency;

/**
 * 货币金额类
 */
public class Money {
    private final BigDecimal amount;
    private final Currency currency;
    
    public Money(BigDecimal amount, Currency currency) {
        this.amount = amount;
        this.currency = currency;
    }
    
    public BigDecimal getAmount() {
        return amount;
    }
    
    public Currency getCurrency() {
        return currency;
    }
    
    @Override
    public String toString() {
        return String.format("%.2f %s", amount, currency.getCurrencyCode());
    }
}
