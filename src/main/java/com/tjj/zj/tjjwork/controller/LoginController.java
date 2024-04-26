package com.tjj.zj.tjjwork.controller;

import com.tjj.zj.tjjwork.entity.User;
import com.tjj.zj.tjjwork.service.LoginService;
import com.tjj.zj.tjjwork.service.UserService;
import com.tjj.zj.tjjwork.util.Constant;
import com.tjj.zj.tjjwork.web.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.postgresql.util.MD5Digest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.security.provider.MD5;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @parma
 */
@Api(tags = "登录页面")
@Slf4j
@RestController
@RequestMapping("/")
public class LoginController {

    @Autowired
    private LoginService loginService;


    @GetMapping("/login")
    @ApiOperation("后台登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "account", value = "用户账号"),
            @ApiImplicitParam(name = "password", value = "用户密码")
    })
    public Object login(@RequestParam("account") String account, @RequestParam("password") String password, HttpServletRequest request)   {
        return loginService.login(account,password,request);

    }

    @PostMapping("/register")
    @ApiOperation("用户注册")
    public Object addUser(@RequestBody User user) {
        return loginService.addUser(user);
    }

}
