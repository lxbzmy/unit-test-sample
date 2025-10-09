package com.foo.currency;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;

/**
 * 货币计算模块
 */
public class CurrencyCalculator {
    private final RateService rateService;
    
    public CurrencyCalculator(RateService config) {
        this.rateService = config;
    }
    
    /**
     * 货币转换
     * @param amount 金额
     * @param fromCurrency 源货币
     * @param toCurrency 目标货币
     * @return 转换结果
     * @throws CurrencyConversionException 转换失败时抛出异常
     */
    public Money convert(BigDecimal amount, String fromCurrency, String toCurrency) 
            throws CurrencyConversionException {
        
        if (amount == null || amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new CurrencyConversionException("Invalid amount: must be positive");
        }
        
        if (fromCurrency == null || toCurrency == null) {
            throw new CurrencyConversionException("Currency codes cannot be null");
        }
        
        fromCurrency = fromCurrency.toUpperCase();
        toCurrency = toCurrency.toUpperCase();
        
        // 验证货币代码是否有效
        Currency toCurrencyObj;
        try {
            Currency.getInstance(fromCurrency);
            toCurrencyObj = Currency.getInstance(toCurrency);
        } catch (IllegalArgumentException e) {
            throw new CurrencyConversionException("Invalid currency code: " + e.getMessage(), e);
        }
        
        // 相同货币直接返回
        if (fromCurrency.equals(toCurrency)) {
            return new Money(amount, toCurrencyObj);
        }
        
        // 获取汇率 (相对于基准货币，通常是USD)
        BigDecimal fromRate = rateService.getRate(fromCurrency);
        BigDecimal toRate = rateService.getRate(toCurrency);
        
        if (fromRate == null) {
            throw new CurrencyConversionException("Exchange rate not found for: " + fromCurrency);
        }
        
        if (toRate == null) {
            throw new CurrencyConversionException("Exchange rate not found for: " + toCurrency);
        }
        
        // 转换计算: amount * (toRate / fromRate)
        BigDecimal convertedAmount = amount.multiply(toRate).divide(fromRate, 2, RoundingMode.HALF_UP);
        
        return new Money(convertedAmount, toCurrencyObj);
    }
}
