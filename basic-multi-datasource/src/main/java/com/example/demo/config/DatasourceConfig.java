package com.example.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:config/jdbc.properties") //指定配置信息文件
public class DatasourceConfig {
    @Bean("master") // 指定主从数据源的bean名称为master、slave
    @ConfigurationProperties(prefix = "spring.datasource.master") // 指定主从配置前缀
    public DataSource masterDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean("slave")
    @ConfigurationProperties(prefix = "spring.datasource.slave")
    public DataSource slaveDataSource() {
        return DataSourceBuilder.create().build();
    }
}
