package com.foo.currency.config;

/**
 * 配置文件读取接口
 */
public interface ConfigReader {
    /**
     * 读取配置文件并返回配置DTO
     * @param configPath 配置文件路径
     * @return 配置DTO
     * @throws Exception 读取失败时抛出异常
     */
    ConfigDto readConfig(String configPath) throws Exception;
}
