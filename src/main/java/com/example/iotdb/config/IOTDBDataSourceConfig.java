package com.example.iotdb.config;


import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.sql.DataSource;

/**
 * @Author: Aiden
 */
@Configuration
//@MapperScan(basePackages = "com.example.iotdb.mapper.iotdb", sqlSessionFactoryRef = "iotdbSqlSessionFactory")
public class IOTDBDataSourceConfig {
    // 采用多个配置方式访问其他的数据库(为其他的数据库使用mybatis框架)
    // 1. 配置多个数据源

    private static final Class<?> LOG_IMPL_CLASS = org.apache.ibatis.logging.stdout.StdOutImpl.class;



    @Bean("iotdbDataSource")
    public DataSource iotdbDataSource() {
        String driverClassName = "org.apache.iotdb.jdbc.IoTDBDriver";
        String url = "jdbc:iotdb://localhost:6667/";
        String username = "root";
        String password = "root";
        return getBasicDataSource(driverClassName, url, username, password);
    }

    private DataSource getBasicDataSource(String driverClassName, String url, String username, String password) {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    // 2. 配置多个SqlSessionFactoryBean



    @Bean("iotdbSqlSessionFactory")
    public SqlSessionFactoryBean iotdbSqlSessionFactory(@Autowired @Qualifier("iotdbDataSource") DataSource dataSource)
            throws Exception {
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setLogImpl((Class<? extends Log>) LOG_IMPL_CLASS);
        configuration.setEnvironment(new Environment("iotdbEnv", new JdbcTransactionFactory(), dataSource));
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setConfiguration(configuration);
        sqlSessionFactory.setDataSource(dataSource);
        // 手动扫描映射文件
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        //sqlSessionFactory.setMapperLocations(new ClassPathResource("mapper/iotdb/*.xml"));
        sqlSessionFactory.setMapperLocations(resolver.getResources("classpath*:/mapper.iotdb/*.xml"));
        return sqlSessionFactory;
    }

    /*@Bean("mysqlDataSource")
    public DataSource mysqlDataSource() {
        String driverClassName = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/demo_sql?serverTimezone=UTC&useUnicode=true&characterEncoding=UTF-8";
        String username = "root";
        String password = "HUANHUAN1994904*";
        return getBasicDataSource(driverClassName, url, username, password);
    }*/


    // 2. 配置多个SqlSessionFactoryBean

   /* @Bean("mysqlSqlSessionFacotry")
    public SqlSessionFactoryBean mysqlSqlSessionFactory(@Autowired @Qualifier("mysqlDataSource") DataSource dataSource)
            throws Exception {
        *//* SqlSessionFactory sqlSessionFactory=new SqlSessionFactoryBuilder(). *//*
        // 与spring整合的过程中只需要SqlSessionFactoryBean这个对象
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setLogImpl((Class<? extends Log>) LOG_IMPL_CLASS);
        configuration.setEnvironment(new Environment("mysqlEnv", new JdbcTransactionFactory(), dataSource));
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setConfiguration(configuration);
        sqlSessionFactory.setDataSource(dataSource);
        //
        *//*
         * MapperScannerConfigurer scanner = new MapperScannerConfigurer();
         * scanner.setSqlSessionFactory(sqlSessionFactory.getObject());
         * scanner.setBasePackage("com.hy.springboot.test.mapper.mysql");
         * scanner.afterPropertiesSet();// 触发操作
         *//*
        // 手动扫描映射文件
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        //sqlSessionFactory.setMapperLocations(new ClassPathResource("mapper/mysql/*.xml"));
        sqlSessionFactory.setMapperLocations(resolver.getResources("classpath*:/mapper.mysql/*.xml"));
        return sqlSessionFactory;

    }

    @Bean("mysqlMapperScanner")
    public MapperScannerConfigurer mysqlMapperScanner(
            @Autowired @Qualifier("mysqlSqlSessionFacotry") SqlSessionFactoryBean sqlSessionFactory) throws Exception {
        MapperScannerConfigurer scanner = new MapperScannerConfigurer();
        scanner.setSqlSessionFactoryBeanName("mysqlSqlSessionFacotry");
        // scanner.setSqlSessionFactory(sqlSessionFactory.getObject());
        scanner.setBasePackage("com.example.iotdb.mapper.mysql");
        return scanner;
    }*/

    @Bean("iotdbMapperScanner")
    public MapperScannerConfigurer iotdbMapperScanner(
            @Autowired @Qualifier("iotdbSqlSessionFactory") SqlSessionFactoryBean sqlSessionFactory) throws Exception {
        MapperScannerConfigurer scanner = new MapperScannerConfigurer();
        // scanner.setSqlSessionFactory(sqlSessionFactory.getObject());
        scanner.setSqlSessionFactoryBeanName("iotdbSqlSessionFactory");
        scanner.setBasePackage("com.example.iotdb.mapper.iotdb");
        return scanner;
    }

}
