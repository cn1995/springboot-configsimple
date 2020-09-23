package com.example.demo;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//


import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;


@Configuration
public class DataSourceCfg {
    private static final Logger logger = LoggerFactory.getLogger(DataSourceCfg.class);

    //dataSource

    //    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Bean("dataSource")
    @ConfigurationProperties(prefix = "spring.datasource.druid.main")
    @Primary
    public DataSource dataSourceMain() {

        logger.info("Init DruidDataSourceMain");
        DruidDataSource build = DruidDataSourceBuilder.create().build();
        return build;
    }

    @Bean("dataSourceSlave")
    @ConfigurationProperties(prefix = "spring.datasource.druid.slave")
    public DataSource dataSourceSlave() {
        logger.info("Init DruidDataSourceSlave");
        return DruidDataSourceBuilder.create().build();
    }
    //----end dataSource  dataSource  dataSourceSlave


}
