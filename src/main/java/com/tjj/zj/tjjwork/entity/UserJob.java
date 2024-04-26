package com.tjj.zj.tjjwork.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @parma
 */
@TableName("zp_apply_info")
@ApiModel("报名表")
@Data
public class UserJob {

    @TableField("user_id")
    @ApiModelProperty("用户ID")
    private String userId;

    @TableField("user_name")
    @ApiModelProperty("用户名")
    private String userName;

    @TableField("phone_num")
    @ApiModelProperty("联系方式")
    private String phoneNum;

    @TableField("company_id")
    @ApiModelProperty("企业id")
    private String companyId;

    @TableField("job_id")
    @ApiModelProperty("岗位id")
    private String jobId;

    @TableField("apply_time")
    @ApiModelProperty("报名时间")
    private String applyTime;

    @TableField("work_status")
    @ApiModelProperty("状态 0：未审批 1：商家同意 2：商家拒绝")
    private String workStatus;
}
