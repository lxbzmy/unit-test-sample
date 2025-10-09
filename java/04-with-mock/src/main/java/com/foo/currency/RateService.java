package com.foo.currency;

import java.math.BigDecimal;

/**
 * 汇率服务接口
 */
public interface RateService {
    /**
     * 获取指定货币的汇率
     * @param currency 货币代码
     * @return 汇率，如果找不到返回null
     */
    BigDecimal getRate(String currency);
}
