package com.example.demo.config;

import com.example.demo.context.DynamicDataSourceContextHolder;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.Map;

public class DynamicDataSource extends AbstractRoutingDataSource {

    private final Map<Object, Object> backupTargetDataSources;

    /**
     * 自定义构造函数
     */
    public DynamicDataSource(DataSource defaultDataSource,Map<Object, Object> targetDataSource){
        backupTargetDataSources = targetDataSource;
        super.setDefaultTargetDataSource(defaultDataSource);
        super.setTargetDataSources(backupTargetDataSources);
        super.afterPropertiesSet();
    }

    /**
     * 添加新数据源
     */
    public void addDataSource(String key, DataSource dataSource){
        this.backupTargetDataSources.put(key,dataSource);
        super.setTargetDataSources(this.backupTargetDataSources);
        super.afterPropertiesSet();
    }

    // 实现路由策略
    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceContextHolder.getContextKey();
    }
}
