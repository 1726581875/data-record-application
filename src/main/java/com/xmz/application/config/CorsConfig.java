package com.xmz.application.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.List;

/**
 * @author xmz
 * @date: 2021/09/30
 * 跨域配置
 */
@Configuration
public class CorsConfig {

    /**
     * 配置允许的域多个则以逗号分隔,如果不配置默认为*
     * 例如：http://localhost:8080,http://localhost:8081
     */
    @Value("${sys.allowOrigins:*}")
    private String allowOrigins;


    /**
     * 跨域配置
     * @return
     */
    @Bean
    public FilterRegistrationBean registerCorsFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        CorsFilter corsFilter = getCorsFilter();
        filterRegistrationBean.setFilter(corsFilter);
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.setName("corsFilter");
        filterRegistrationBean.setOrder(-1);
        return filterRegistrationBean;
    }



    private CorsFilter getCorsFilter() {
        //所配置允许的域，默认为*（所有）
        List<String> allowOriginList = Arrays.asList(allowOrigins.split(","));
        CorsConfiguration config = new CorsConfiguration();
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        //允许携带cookie
        config.setAllowCredentials(true);
        //允许访问的域
        config.setAllowedOriginPatterns(allowOriginList);
        //允许访问的请求头
        config.addAllowedHeader("*");
        //允许访问的请方法
        config.addAllowedMethod("*");
        //路径
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

}
