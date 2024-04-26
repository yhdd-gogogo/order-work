package com.tjj.zj.tjjwork.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tjj.zj.tjjwork.util.ModelTree.TreeNode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @parma
 */
@Data
@TableName("zp_role_info")
@ApiModel("角色")
public class Role {

    @TableField("role_id")
    @ApiModelProperty("角色Id")
    private String roleId;

    @TableField("role_name")
    @ApiModelProperty("角色名称")
    private String roleName;

    @TableField("role_description")
    @ApiModelProperty("角色描述")
    private String roleDescription;

    @TableField("create_time")
    @ApiModelProperty("创建时间")
    private String createTime;

    @TableField(exist = false)
    @ApiModelProperty("菜单树")
    private List<TreeNode> menus;
}
