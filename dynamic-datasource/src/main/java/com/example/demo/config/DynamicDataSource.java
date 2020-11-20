package com.example.demo.config;

import com.example.demo.context.DynamicDataSourceContextHolder;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 动态数据源
 * @author zsw
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    /**
     * 实现路由策略
     * @return
     */
    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceContextHolder.getContextKey();
    }
}
