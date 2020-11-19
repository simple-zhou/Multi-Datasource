package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.constants.DataSourceConstants;
import com.example.demo.context.DynamicDataSourceContextHolder;
import com.example.demo.entity.TestUser;
import com.example.demo.mapper.TestUserMapper;
import com.example.demo.vo.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class TestUserController {

    @Autowired
    private TestUserMapper testUserMapper;

    @GetMapping("/listall")
    public Object listAll() {
        int initSize = 2;
        Map<String, Object> result = new HashMap<>(initSize);
        // 默认master查询
        QueryWrapper<TestUser> queryWrapper = new QueryWrapper<>();
        List<TestUser> resultData = testUserMapper.selectAll(queryWrapper.isNotNull("name"));
        result.put(DataSourceConstants.DS_KEY_MASTER, resultData);

        // 切换数据源，在slave查询
        DynamicDataSourceContextHolder.setContextKey(DataSourceConstants.DS_KEY_SLAVE);
        List<TestUser> resultDataSlave = testUserMapper.selectList(null);
        result.put(DataSourceConstants.DS_KEY_SLAVE,resultDataSlave);
        // 恢复数据源
        DynamicDataSourceContextHolder.removeContextKey();
        // 返回数据

        return ResponseResult.success(result);
    }
}

