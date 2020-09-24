package com.example.demo.mybatis.config;

import com.example.demo.mybatis.annotation.Master;
import com.example.demo.mybatis.annotation.Slave;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import javax.transaction.TransactionManager;


@Configuration
public class MybatisConfigMain {

    @Bean
    @Primary
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setTypeAliasesPackage("com.example.demo.master");
       // sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResource("classpath:/mapper/master/**.xml"));
        sqlSessionFactoryBean.setConfigLocation(new PathMatchingResourcePatternResolver().getResource("classpath:config/settings.xml"));


        return sqlSessionFactoryBean.getObject();
    }


    @Bean
    public SqlSessionFactory sqlSessionFactorySlave(@Qualifier("dataSourceSlave") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setTypeAliasesPackage("com.example.demo.slave");
        //sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResource("classpath:/mapper/slave/**.xml"));
        sqlSessionFactoryBean.setConfigLocation(new PathMatchingResourcePatternResolver().getResource("classpath:config/settings.xml"));

        return sqlSessionFactoryBean.getObject();
    }

    @Bean("mapperScannerConfigurer")
    @Primary
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("com.example.demo");
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        mapperScannerConfigurer.setAnnotationClass(Master.class);

        return mapperScannerConfigurer;
    }

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurerSlave() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("com.example.demo");
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactorySlave");
        mapperScannerConfigurer.setAnnotationClass(Slave.class);
        return mapperScannerConfigurer;
    }

    @Bean
    public DataSourceTransactionManager dataSourceTransactionManager(@Qualifier("dataSource") DataSource dataSource) {

        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public DataSourceTransactionManager dataSourceTransactionManagerSlave(@Qualifier("dataSourceSlave") DataSource dataSource) {

        return new DataSourceTransactionManager(dataSource);
    }


}
