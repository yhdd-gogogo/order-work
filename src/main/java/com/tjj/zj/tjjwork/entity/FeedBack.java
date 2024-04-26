package com.tjj.zj.tjjwork.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @parma
 */
@TableName("zp_feedback_info")
@ApiModel("反馈")
@Data
public class FeedBack {

    @TableField("user_id")
    @ApiModelProperty("反馈人id")
    private String userId;

    @TableField("user_name")
    @ApiModelProperty("反馈人姓名")
    private String userName;

    @TableField("job_id")
    @ApiModelProperty("被反馈岗位id")
    private String jobId;

    @TableField("job_name")
    @ApiModelProperty("被反馈岗位名称")
    private String jobName;

    @TableField("company_id")
    @ApiModelProperty("企业统一信用代码")
    private String companyId;

    @TableField("company_name")
    @ApiModelProperty("企业名称")
    private String companyName;

    @TableField("phone_num")
    @ApiModelProperty("反馈人联系方式")
    private String phoneNum;

    @TableField("status")
    @ApiModelProperty("反馈信息状态 0：待处理 1：已处理")
    private String status;

    @TableField("content")
    @ApiModelProperty("反馈内容")
    private String content;

    @TableField("feedback_time")
    @ApiModelProperty("反馈时间")
    private String feedbackTime;

    @TableField("deal_time")
    @ApiModelProperty("处理时间")
    private String dealTime;

    @TableField("deal_content")
    @ApiModelProperty("处理意见")
    private String dealContent;
}
