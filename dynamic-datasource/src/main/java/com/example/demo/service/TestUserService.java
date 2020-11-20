package com.example.demo.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.annotation.DS;
import com.example.demo.constants.DataSourceConstants;
import com.example.demo.entity.TestUser;
import com.example.demo.mapper.TestUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestUserService {

    @Autowired
    TestUserMapper testUserMapper;

    @DS(DataSourceConstants.DS_KEY_MASTER)
    public List<TestUser> getMasterUser() {
        QueryWrapper<TestUser> queryWrapper = new QueryWrapper<>();
        return testUserMapper.selectAll(queryWrapper.isNotNull("name"));
    }

    /**
     * 查询slave库user
     */
    @DS(DataSourceConstants.DS_KEY_SLAVE)
    public List<TestUser> getSlaveUser() {
        return testUserMapper.selectList(null);
    }

}
