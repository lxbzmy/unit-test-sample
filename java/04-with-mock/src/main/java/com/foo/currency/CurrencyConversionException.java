package com.foo.currency;

/**
 * 货币转换异常
 */
public class CurrencyConversionException extends Exception {
    
    public CurrencyConversionException(String message) {
        super(message);
    }
    
    public CurrencyConversionException(String message, Throwable cause) {
        super(message, cause);
    }
}
