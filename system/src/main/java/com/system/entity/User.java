package com.system.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Id;
import lombok.Data;

@ApiModel(value = "com.system.entity.User")
@Data
public class User implements Serializable {
    @Id
    @Column(name = "`id`")
    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "userName")
    private String userName;

    @ApiModelProperty(value = "userPhone")
    private String userPhone;

    @ApiModelProperty(value = "userPassword")
    private String userPassword;

    private static final long serialVersionUID = 1L;

}