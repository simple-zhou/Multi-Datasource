package com.example.demo.controller;

import com.example.demo.context.DynamicDataSourceContextHolder;
import com.example.demo.mapper.TableMapper;
import com.example.demo.proxy.JdkParamDsMethodProxy;
import com.example.demo.util.DataSourceUtil;
import com.example.demo.vo.DbInfo;
import com.example.demo.vo.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class TestController {

    @Autowired
    private TableMapper tableMapper;

    @GetMapping("table")
    public Object findWithDbInfo(DbInfo dbInfo) throws Exception {
        /*//数据源key
        String newDsKey = System.currentTimeMillis()+"";
        //添加数据源
        DataSourceUtil.addDataSourceToDynamic(newDsKey,dbInfo);
        DynamicDataSourceContextHolder.setContextKey(newDsKey);
        //查询表信息
        List<Map<String, Object>> tables = tableMapper.selectTableList();
        DynamicDataSourceContextHolder.removeContextKey();
        return ResponseResult.success(tables);*/

        //数据源key
        String newDsKey = System.currentTimeMillis()+"";
        //使用代理切换数据源
        TableMapper tableMapperProxy = (TableMapper) JdkParamDsMethodProxy.createProxyInstance(tableMapper,newDsKey,dbInfo);
        List<Map<String, Object>> tables = tableMapperProxy.selectTableList();
        return ResponseResult.success(tables);
    }
}
