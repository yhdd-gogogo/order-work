package com.tjj.zj.tjjwork.controller;

import com.tjj.zj.tjjwork.service.MenuService;
import com.tjj.zj.tjjwork.util.ModelTree.TreeNode;
import com.tjj.zj.tjjwork.web.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @parma
 */
@Api(tags = "菜单功能")
@Slf4j
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping("findMenuTree")
    @ApiOperation("查询菜单树")
    public Object findMenuTree() {
        TreeNode treeNode = menuService.findMenuTree();
        return CommonResult.success(treeNode.getChildren());
    }

    @GetMapping("userMenu")
    @ApiOperation("用户菜单")
    public Object userMenu() {
        TreeNode treeNode = menuService.userMenu();
        return CommonResult.success(treeNode.getChildren());
    }

}
