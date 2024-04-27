package com.tjj.zj.tjjwork.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tjj.zj.tjjwork.entity.User;
import com.tjj.zj.tjjwork.service.UserService;
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
@Api(tags = "用户管理")
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/findByPage")
    @ApiOperation("用户列表分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "当前页"),
            @ApiImplicitParam(name = "size", value = "每页显示记录条数"),
            @ApiImplicitParam(name = "params", value = "额外参数"),
            @ApiImplicitParam(name = "account", value = "用户账号 精确匹配 可选"),
            @ApiImplicitParam(name = "userType", value = "用户类型"),
            @ApiImplicitParam(name = "userStatus", value = "账号状态"),
    })
    public Object findByPage(@RequestParam Map<String, Object> params,
                             @RequestParam(defaultValue = "1") Integer page,
                             @RequestParam(defaultValue = "10") Integer size) {
        Page<User> pageResult = userService.findByPage(params, page, size);
        return CommonResult.success(pageResult);
    }

    @GetMapping("/freeze")
    @ApiOperation("管理员冻结用户")
    public Object freezeUser(@RequestParam("accountId") String accountId) {
        return userService.freezeUser(accountId);
    }

    @GetMapping("/valid")
    @ApiOperation("管理员解冻/激活用户")
    public Object validUser(@RequestParam("accountId") String accountId) {
        return userService.validUser(accountId);
    }

    @GetMapping("/detail")
    @ApiOperation("查看用户")
    public Object detailUser(@RequestParam("userId") String userId) {
        return userService.detailUser(userId);
    }

    @PostMapping("/update")
    @ApiOperation("修改信息")
    public Object updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }
}
