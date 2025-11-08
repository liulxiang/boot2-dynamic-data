package com.example.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * Druid配置类
 */
@Configuration
public class DruidConfig {

    /**
     * 配置Druid监控界面
     */
    @Bean
    public ServletRegistrationBean<StatViewServlet> statViewServlet() {
        ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");
        Map<String, String> initParams = new HashMap<>();
        
        // 配置监控界面登录用户名和密码
        initParams.put("loginUsername", "admin");
        initParams.put("loginPassword", "admin");
        // 允许访问的IP地址，不写默认所有IP都可以访问
        initParams.put("allow", "");
        // 拒绝访问的IP地址
        initParams.put("deny", "");
        
        bean.setInitParameters(initParams);
        return bean;
    }

    /**
     * 配置Web监控
     */
    @Bean
    public FilterRegistrationBean<WebStatFilter> webStatFilter() {
        FilterRegistrationBean<WebStatFilter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new WebStatFilter());
        
        Map<String, String> initParams = new HashMap<>();
        // 排除监控的请求
        initParams.put("exclusions", "*.js,*.css,/druid/*");
        
        bean.setInitParameters(initParams);
        bean.setUrlPatterns(java.util.Arrays.asList("/*"));
        return bean;
    }
}