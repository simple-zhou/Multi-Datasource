package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.example.demo.entity.master.MasterTestUser;
import com.example.demo.entity.slave.SlaveTestUser;
import com.example.demo.mapper.master.MasterTestUserMapper;
import com.example.demo.mapper.slave.SlaveTestUserMapper;
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
    private MasterTestUserMapper masterTestUserMapper;

    @Autowired
    private SlaveTestUserMapper slaveTestUserMapper;

    @GetMapping("/listall")
    public Object listAll() {
        // master库，自定义接口查询
        QueryWrapper<MasterTestUser> queryWrapper = new QueryWrapper<>();
        List<MasterTestUser> resultDataMaster = masterTestUserMapper.selectAll(queryWrapper.isNotNull("name"));
        // slave库，mp内置接口
        List<SlaveTestUser> resultDataSlave = slaveTestUserMapper.selectList(null);
        // 返回
        Map<String,Object> result = new HashMap<>();
        result.put("master",resultDataMaster);
        result.put("slave",resultDataSlave);
        return ResponseResult.success(result);
    }
}

