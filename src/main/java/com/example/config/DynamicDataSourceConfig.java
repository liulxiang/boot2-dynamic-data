package com.example.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.dynamic.datasource.DynamicRoutingDataSource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 动态数据源配置类
 */
@Configuration
public class DynamicDataSourceConfig {

    /**
     * 当启用多数据源时，创建动态数据源
     */
    @Bean
    @ConditionalOnProperty(name = "spring.datasource.dynamic.enabled", havingValue = "true", matchIfMissing = true)
    public DataSource dynamicDataSource() {
        return new DynamicRoutingDataSource();
    }

    /**
     * 当禁用多数据源时，创建默认数据源
     */
    @Bean
    @ConditionalOnProperty(name = "spring.datasource.dynamic.enabled", havingValue = "false")
    @ConfigurationProperties(prefix = "spring.datasource.dynamic.datasource.master")
    public DataSource singleDataSource() {
        return new DruidDataSource();
    }
}