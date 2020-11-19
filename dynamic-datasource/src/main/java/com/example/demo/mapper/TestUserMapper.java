package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.example.demo.entity.TestUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestUserMapper extends BaseMapper<TestUser> {
    /**
     * 自定义查询
     * @param wrapper 条件构造器
     */
    List<TestUser> selectAll(@Param(Constants.WRAPPER) Wrapper<TestUser> wrapper);
}
