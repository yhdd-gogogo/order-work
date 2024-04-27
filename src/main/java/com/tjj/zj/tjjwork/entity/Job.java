package com.tjj.zj.tjjwork.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @parma
 */
@TableName("zp_work_info")
@ApiModel("岗位")
@Data
public class Job {

    @TableField("company_id")
    @ApiModelProperty("企业统一信用代码")
    private String companyId;

    @TableField("company_name")
    @ApiModelProperty("企业名称")
    private String companyName;

    @TableField("job_name")
    @ApiModelProperty("岗位名称")
    private String jobName;

    @TableField("job_type")
    @ApiModelProperty("岗位类别")
    private String jobType;

    @TableField("link_man")
    @ApiModelProperty("联系人")
    private String linkMan;

    @TableField("phone_num")
    @ApiModelProperty("联系方式")
    private String phoneNum;

    @TableField("release_time")
    @ApiModelProperty("发布时间")
    private String releaseTime;

    @TableField("pay_way")
    @ApiModelProperty("结算方式")
    private String payWay;

    @TableField("job_require")
    @ApiModelProperty("岗位要求")
    private String jobRequire;

    @TableField("job_status")
    @ApiModelProperty("岗位状态 0：未审批 1：同意 2:打回")
    private String jobStatus;

    @TableField("job_id")
    @ApiModelProperty("岗位Id")
    private String jobId;

    @TableField(exist = false)
    @ApiModelProperty("申请状态 0：已申请未审批 1：已同意 2：已拒绝")
    private String workStatus;
}
