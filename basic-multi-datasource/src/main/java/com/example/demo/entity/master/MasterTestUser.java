package com.example.demo.entity.master;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("test_user")
public class MasterTestUser implements Serializable {

    private static final long serialVersionUID = -5635137176744743334L;

    private long id;

    private String name;
}
