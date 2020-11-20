package com.example.demo.vo;

import lombok.Data;

/**
 * 数据库连接信息
 */
@Data
public class DbInfo {
    private String ip;
    private String port;
    private String dbName;
    private String driveClassName;
    private String username;
    private String password;
}
