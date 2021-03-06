package com.example.demo.mapper.master;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.example.demo.entity.master.MasterTestUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MasterTestUserMapper extends BaseMapper<MasterTestUser> {
    /**
     * 自定义查询
     * @param wrapper 条件构造器
     */
    List<MasterTestUser> selectAll(@Param(Constants.WRAPPER) Wrapper<MasterTestUser> wrapper);
}
