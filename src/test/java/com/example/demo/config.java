package com.example.demo;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import com.alibaba.druid.filter.config.ConfigFilter;
import com.alibaba.druid.filter.encoding.EncodingConvertFilter;
import com.alibaba.druid.filter.logging.CommonsLogFilter;
import com.alibaba.druid.filter.logging.Log4j2Filter;
import com.alibaba.druid.filter.logging.Log4jFilter;
import com.alibaba.druid.filter.logging.Slf4jLogFilter;
import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.wall.WallConfig;
import com.alibaba.druid.wall.WallFilter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class config {
    @Bean
    @ConfigurationProperties("spring1.datasource.druid.filter.stat")
    @ConditionalOnProperty(
            prefix = "spring1.datasource.druid.filter.stat",
            name = {"enabled"},
            matchIfMissing = true
    )
    @ConditionalOnMissingBean
    public StatFilter statFilter() {
        return new StatFilter();
    }

    @Bean
    @ConfigurationProperties("spring1.datasource.druid.filter.config")
    @ConditionalOnProperty(
            prefix = "spring1.datasource.druid.filter.config",
            name = {"enabled"}
    )
    @ConditionalOnMissingBean
    public ConfigFilter configFilter() {
        return new ConfigFilter();
    }

}
