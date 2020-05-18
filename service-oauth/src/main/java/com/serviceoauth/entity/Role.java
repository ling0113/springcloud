package com.serviceoauth.entity;


import lombok.Data;

/**
 * 角色表
 */
@Data
public class Role {

    private Integer id;
    //角色标识
    private String name;
    //角色名称
    private String nameZh;
}