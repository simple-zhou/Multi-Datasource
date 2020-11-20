package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.TestUser;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface TableMapper extends BaseMapper<TestUser> {
    /**
     * 查询表信息
     */
    @Select("select table_name, table_comment, create_time, update_time " +
            " from information_schema.tables " +
            " where table_schema = (select database())")
    List<Map<String,Object>> selectTableList();
}
