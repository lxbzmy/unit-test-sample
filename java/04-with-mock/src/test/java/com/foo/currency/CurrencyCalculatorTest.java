package com.foo.currency;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

/**
 * CurrencyMain 的单元测试，演示如何 mock 依赖组件
 */
@ExtendWith(MockitoExtension.class)
public class CurrencyCalculatorTest {
    
    @Mock//←这个是待测对象（sut）mockito框架会自己创建好
    private RateService mockRateService;

    @InjectMocks//←框架会自动创建类实例，并且注入依赖
    com.foo.currency.CurrencyCalculator sut;

    @Test
    public void testRunWithValidInput() throws Exception {
        // given
        when(mockRateService.getRate("CNY")).thenReturn(new BigDecimal("7.20"));
        when(mockRateService.getRate("EUR")).thenReturn(new BigDecimal("0.85"));

        // when
        Money result = sut.convert(BigDecimal.valueOf(100.00), "CNY", "EUR");
        // then
        assertEquals("11.81", result.getAmount().toString());
        assertEquals("EUR", result.getCurrency().toString());
    }
    
}
