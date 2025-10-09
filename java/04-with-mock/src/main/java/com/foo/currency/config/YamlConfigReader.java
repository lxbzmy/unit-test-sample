package com.foo.currency.config;

import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * YAML配置文件读取实现类
 */
public class YamlConfigReader implements ConfigReader {
    
    @Override
    public ConfigDto readConfig(String configPath) throws Exception {
        Yaml yaml = new Yaml();
        
        try (InputStream inputStream = new FileInputStream(configPath)) {
            Map<String, Object> data = yaml.load(inputStream);
            
            // 解析汇率数据
            Map<String, BigDecimal> exchangeRates = new HashMap<>();
            if (data.containsKey("exchangeRates")) {
                Map<String, Object> rates = (Map<String, Object>) data.get("exchangeRates");
                for (Map.Entry<String, Object> entry : rates.entrySet()) {
                    exchangeRates.put(entry.getKey(), new BigDecimal(entry.getValue().toString()));
                }
            }
            
            // 解析其他配置
            Map<String, Object> otherConfigs = new HashMap<>();
            for (Map.Entry<String, Object> entry : data.entrySet()) {
                if (!"exchangeRates".equals(entry.getKey())) {
                    otherConfigs.put(entry.getKey(), entry.getValue());
                }
            }
            
            return new ConfigDto(exchangeRates, otherConfigs);
        }
    }
}
