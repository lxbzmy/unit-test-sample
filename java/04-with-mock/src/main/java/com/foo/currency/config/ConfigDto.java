package com.foo.currency.config;

import com.foo.currency.RateService;

import java.math.BigDecimal;
import java.util.Map;

/**
 * 配置数据传输对象
 */
public class ConfigDto implements RateService {
    private Map<String, BigDecimal> exchangeRates;
    private Map<String, Object> otherConfigs;
    
    public ConfigDto() {
    }
    
    public ConfigDto(Map<String, BigDecimal> exchangeRates, Map<String, Object> otherConfigs) {
        this.exchangeRates = exchangeRates;
        this.otherConfigs = otherConfigs;
    }
    
    public Map<String, BigDecimal> getExchangeRates() {
        return exchangeRates;
    }
    
    public void setExchangeRates(Map<String, BigDecimal> exchangeRates) {
        this.exchangeRates = exchangeRates;
    }
    
    public Map<String, Object> getOtherConfigs() {
        return otherConfigs;
    }
    
    public void setOtherConfigs(Map<String, Object> otherConfigs) {
        this.otherConfigs = otherConfigs;
    }
    
    @Override
    public BigDecimal getRate(String currency) {
        if ("USD".equals(currency)) {
            return BigDecimal.ONE; // USD作为基准货币
        }
        return exchangeRates != null ? exchangeRates.get(currency) : null;
    }
}
