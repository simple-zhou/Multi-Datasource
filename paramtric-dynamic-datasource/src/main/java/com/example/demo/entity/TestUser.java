package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("test_user")
public class TestUser implements Serializable {

    private static final long serialVersionUID = 90088779547995361L;
    private long id;
    private String name;
}
