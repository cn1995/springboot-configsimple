package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Map;
import java.util.Properties;

/**
 * 第一个数据源，jpa的相关配置
 */
@Configuration
//1、实体扫描
//2、实体管理ref
//3、事务管理
@EnableJpaRepositories(
        basePackages = "com.example.demo.slave",
        entityManagerFactoryRef = "secondEntityManagerFactoryBean",
        transactionManagerRef = "secondTransactionManager")
@EnableTransactionManagement

public class SlaveDataSourceCfg {

    //第一个数据源，可以不加Qualifier
    @Autowired
    @Qualifier("dataSourceSlave")
    private DataSource dataSourceSlave;

    //jpa其他参数配置
    @Autowired
    private JpaProperties jpaProperties;

    //实体管理工厂builder
    @Autowired
    private EntityManagerFactoryBuilder factoryBuilder;

    @Autowired
    HibernateProperties hibernateProperties;

    /**
     * 配置第一个实体管理工厂的bean
     *
     * @return
     */
    @Bean(name = "secondEntityManagerFactoryBean")

    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
        Map<String, Object> stringObjectMap = hibernateProperties.determineHibernateProperties(jpaProperties.getProperties(), new HibernateSettings());

        LocalContainerEntityManagerFactoryBean secondPersistenceUnit = factoryBuilder.dataSource(dataSourceSlave)
                //这一行的目的是加入jpa的其他配置参数比如（ddl-auto: update等）
                //当然这个参数配置可以在事务配置的时候也可以
                .properties(stringObjectMap)
                // .properties(hibernateProperties)
                .packages("com.example.demo.master")
                .persistenceUnit("secondPersistenceUnit")
                .build();
        return secondPersistenceUnit;
    }

    /**
     * EntityManager不过解释，用过jpa的应该都了解
     *
     * @return
     */
    @Bean(name = "secondEntityManager")

    public EntityManager entityManager() {
        return entityManagerFactoryBean().getObject().createEntityManager();
    }

    /**
     * jpa事务管理
     *
     * @return
     */
    @Bean(name = "secondTransactionManager")

    public JpaTransactionManager transactionManager() {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(entityManagerFactoryBean().getObject());
        return jpaTransactionManager;
    }
}
