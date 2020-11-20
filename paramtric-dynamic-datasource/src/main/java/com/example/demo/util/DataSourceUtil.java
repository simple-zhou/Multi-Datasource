package com.example.demo.util;

import com.example.demo.config.DynamicDataSource;
import com.example.demo.context.SpringContextHolder;
import com.example.demo.vo.DbInfo;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;

public class DataSourceUtil {
    /**
     * 创建新的数据源
     * @param dbInfo
     * @return
     */
    public static DataSource makenewDataSource(DbInfo dbInfo) {
        String url = "jdbc:mysql://"+dbInfo.getIp() + ":"+dbInfo.getPort()+"/"+dbInfo.getDbName()
                +"?useSSL=false&serverTimezone=Asia/Shanghai&characterEncoding=UTF-8";
        String driveClassName = StringUtils.isEmpty(dbInfo.getDriveClassName())? "com.mysql.cj.jdbc.Driver":dbInfo.getDriveClassName();
        return DataSourceBuilder.create().url(url)
                .driverClassName(driveClassName)
                .username(dbInfo.getUsername())
                .password(dbInfo.getPassword())
                .build();
    }

    /**
     * 添加数据源到动态源中
     * @param key
     * @param dataSource
     */
    public static void addDataSourceToDynamic(String key,DataSource dataSource) {
        DynamicDataSource dynamicDataSource = SpringContextHolder.getContext().getBean(DynamicDataSource.class);
        dynamicDataSource.addDataSource(key,dataSource);
    }

    /**
     * 根据数据库连接信息添加数据源到动态源中
     * @param key
     * @param dbInfo
     */
    public static void addDataSourceToDynamic(String key,DbInfo dbInfo) {
        DataSource dataSource = makenewDataSource(dbInfo);
        addDataSourceToDynamic(key,dataSource);
    }

}
