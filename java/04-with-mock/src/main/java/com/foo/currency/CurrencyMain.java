package com.foo.currency;

import com.foo.currency.config.ConfigDto;
import com.foo.currency.config.ConfigReader;
import com.foo.currency.config.YamlConfigReader;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 货币转换应用主程序
 * 使用方式: java -jar xx.jar 100CNY USD
 */
public class CurrencyMain {
    private static final String DEFAULT_CONFIG_PATH = "config.yaml";
    private static final Pattern AMOUNT_CURRENCY_PATTERN = Pattern.compile("(\\d+(?:\\.\\d+)?)([A-Z]{3})");
    
    private final ConfigReader configReader;
    private CurrencyCalculator calculator;
    
    public CurrencyMain(ConfigReader configReader) {
        this.configReader = configReader;
    }
    
    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Usage: java -jar currency-converter.jar <amount><fromCurrency> <toCurrency>");
            System.err.println("Example: java -jar currency-converter.jar 100CNY USD");
            System.exit(1);
        }
        
        CurrencyMain app = new CurrencyMain(new YamlConfigReader());
        app.run(args[0], args[1]);
    }
    
    public void run(String amountWithCurrency, String toCurrency) {
        try {
            // 1. 初始化配置读取类
            System.out.println("Loading configuration from " + DEFAULT_CONFIG_PATH + "...");
            ConfigDto config = configReader.readConfig(DEFAULT_CONFIG_PATH);
            
            // 2. 构造货币计算模块
            this.calculator = new CurrencyCalculator(config);
            
            // 3. 解析输入参数
            ParsedInput input = parseInput(amountWithCurrency, toCurrency);
            if (input == null) {
                System.err.println("Invalid input format. Expected format: 100RMB");
                System.exit(1);
            }
            
            // 4. 执行货币转换
            System.out.printf("Converting %.2f %s to %s...%n", 
                input.amount, input.fromCurrency, input.toCurrency);
            
            try {
                Money result = calculator.convert(
                    input.amount, input.fromCurrency, input.toCurrency);
                
                // 5. 输出结果
                System.out.printf("Result: %s%n", result);
                
            } catch (CurrencyConversionException e) {
                System.err.printf("Conversion failed: %s%n", e.getMessage());
                System.exit(1);
            }
            
        } catch (Exception e) {
            System.err.println("Application error: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }
    
    private ParsedInput parseInput(String amountWithCurrency, String toCurrency) {
        Matcher matcher = AMOUNT_CURRENCY_PATTERN.matcher(amountWithCurrency);
        if (!matcher.matches()) {
            return null;
        }
        
        BigDecimal amount = new BigDecimal(matcher.group(1));
        String fromCurrency = matcher.group(2);
        
        return new ParsedInput(amount, fromCurrency, toCurrency.toUpperCase());
    }
    
    // 内部类，用于封装解析后的输入参数
    private static class ParsedInput {
        final BigDecimal amount;
        final String fromCurrency;
        final String toCurrency;
        
        ParsedInput(BigDecimal amount, String fromCurrency, String toCurrency) {
            this.amount = amount;
            this.fromCurrency = fromCurrency;
            this.toCurrency = toCurrency;
        }
    }
}
