package com.tjj.zj.tjjwork.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;



@TableName("zp_user_info")
@ApiModel("用户")
@Data
public class User {


    @TableField("user_id")
    @ApiModelProperty("用户ID")
    private String userId;

    @TableField("account")
    @ApiModelProperty("账号")
    private String account;

    @TableField("login_name")
    @ApiModelProperty("用户名称")
    private String loginName;

    @TableField("password")
    @ApiModelProperty("密码")
    private String password;

    @TableField("edu")
    @ApiModelProperty("学历")
    private String edu;

    @TableField("phone_num")
    @ApiModelProperty("手机号")
    private String phoneNum;

    @TableField("user_type")
    @ApiModelProperty("用户类型 0：系统管理员 1：个人用户 2：企业用户")
    private String userType;

    @TableField("user_status")
    @ApiModelProperty("用户状态 0：未激活 1：激活")
    private String userStatus;

}
