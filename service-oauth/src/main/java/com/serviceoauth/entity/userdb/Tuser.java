package com.serviceoauth.entity.userdb;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@ApiModel(value = "com.securitydemo1.entity.userdb.Tuser")
@Table(name = "`t_user`")
@Data
public class Tuser implements Serializable {

    /**
     * 用户id
     */
    @Id
    @Column(name = "`id`")
    @ApiModelProperty(value = "id用户id")
    private Long id;

    @Column(name = "`username`")
    @ApiModelProperty(value = "username")
    private String username;

    @Column(name = "`password`")
    @ApiModelProperty(value = "password")
    private String password;

    /**
     * 用户姓名
     */
    @Column(name = "`fullname`")
    @ApiModelProperty(value = "fullname用户姓名")
    private String fullname;

    /**
     * 手机号
     */
    @Column(name = "`mobile`")
    @ApiModelProperty(value = "mobile手机号")
    private String mobile;

    private static final long serialVersionUID = 1L;


}