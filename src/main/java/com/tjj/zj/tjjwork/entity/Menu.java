package com.tjj.zj.tjjwork.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tjj.zj.tjjwork.util.ModelTree.Tree;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @parma
 */
@Data
@TableName("zp_menu_info")
@ApiModel("角色")
public class Menu implements Tree {

    @TableField("menu_id")
    @ApiModelProperty("菜单id")
    private String menuId;

    @TableField("menu_name")
    @ApiModelProperty("菜单名称")
    private String menuName;

    @TableField("menu_lvl")
    @ApiModelProperty("菜单级别")
    private String menuLvl;

    @TableField("menu_code")
    @ApiModelProperty("菜单编码")
    private String menuCode;

    @TableField("menu_par_code")
    @ApiModelProperty("菜单父级编码")
    private String menuParCode;

    @TableField("menu_des")
    @ApiModelProperty("菜单描述")
    private String menuDes;

    @TableField("menu_order")
    @ApiModelProperty("菜单顺序")
    private String menuOrder;


    @Override
    public String fetchCurrentNodeId() {
        return menuCode; // id
    }

    @Override
    public String fetchParentNodeId() {
        return menuParCode; // 父id
    }
}
