package com.tjj.zj.tjjwork.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @parma
 */
@TableName("zp_comp_info")
@ApiModel("企业")
@Data
public class Company {

    @TableField("company_id")
    @ApiModelProperty("企业统一信用代码")
    private String companyId;

    @TableField("company_name")
    @ApiModelProperty("企业名称")
    private String companyName;

    @TableField("company_type")
    @ApiModelProperty("所属行业类型")
    private String companyType;

    @TableField("company_scale")
    @ApiModelProperty("企业规模")
    private String companyScale;

    @TableField("phone_num")
    @ApiModelProperty("联系方式")
    private String phoneNum;

    @TableField("create_time")
    @ApiModelProperty("企业注册时间")
    private String createTime;

    @TableField("status")
    @ApiModelProperty("状态 0：未审批 1：已审批")
    private String status;
}
