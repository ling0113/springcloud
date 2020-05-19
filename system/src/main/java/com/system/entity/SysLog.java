package com.system.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import lombok.Data;

@ApiModel(value="com.system.entity.log")
@Table(name = "`t_sys_log`")
@Data
public class SysLog implements Serializable {
    /**
     * 主键
     */
    @Id
    @Column(name = "`id`")
    @ApiModelProperty(value="id主键")
    private Integer id;

    /**
     * 用时时间/s
     */
    @Column(name = "`elapsed_time`")
    @ApiModelProperty(value="elapsed_time用时时间")
    private Integer elapsedTime;

    /**
     * 操作员id
     */
    @Column(name = "`user_id`")
    @ApiModelProperty(value="userId操作员id")
    private String userId;

    /**
     * 用户操作
     */
    @Column(name = "`user_action`")
    @ApiModelProperty(value="userAction用户操作")
    private String userAction;

    /**
     * 创建时间
     */
    @Column(name = "`create_time`")
    @ApiModelProperty(value="createTime创建时间")
    private Date createTime;

}