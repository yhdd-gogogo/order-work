package com.tjj.zj.tjjwork.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tjj.zj.tjjwork.entity.Job;
import com.tjj.zj.tjjwork.entity.Role;
import com.tjj.zj.tjjwork.service.RoleService;
import com.tjj.zj.tjjwork.web.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @parma
 */
@Api(tags = "角色功能")
@Slf4j
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping
    @ApiOperation("保存角色")
    public Object save(@RequestBody Role role){
        return roleService.save(role);
    }

    @GetMapping("/findByPage")
    @ApiOperation("角色列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "当前页"),
            @ApiImplicitParam(name = "size", value = "每页显示记录条数"),
            @ApiImplicitParam(name = "params", value = "额外参数"),
            @ApiImplicitParam(name = "roleName", value = "角色名称")
    })
    public Object findByPage(@RequestParam Map<String, Object> params,
                             @RequestParam(defaultValue = "1") Integer page,
                             @RequestParam(defaultValue = "10") Integer size) {
        Page<Role> pageResult = roleService.findByPage(params, page, size);
        return CommonResult.success(pageResult);
    }


    @GetMapping("setRole")
    @ApiOperation("设置角色")
    public Object setRole(@RequestParam("userId") String userId,
                          @RequestParam("roleId") String roleId){
        return roleService.setRole(userId,roleId);
    }

    @GetMapping("delete")
    @ApiOperation("删除角色")
    public Object deleteRole(@RequestParam("roleId") String roleId){
        return roleService.deleteRole(roleId);
    }
}
