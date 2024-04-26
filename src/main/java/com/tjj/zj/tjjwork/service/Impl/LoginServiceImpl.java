package com.tjj.zj.tjjwork.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tjj.zj.tjjwork.entity.User;
import com.tjj.zj.tjjwork.service.LoginService;
import com.tjj.zj.tjjwork.service.UserService;
import com.tjj.zj.tjjwork.util.Constant;
import com.tjj.zj.tjjwork.util.MD5Utils;
import com.tjj.zj.tjjwork.web.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.ObjectStreamClass;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @parma
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserService userService;

    @Override
    public Object login(String account, String password, HttpServletRequest request)  {
        User user = userService.findByAccount(account);
        if (user == null) {
            return CommonResult.failed("用户不存在");
        }

        String finalPassword = MD5Utils.encrpty(password);

        if (user.getPassword().equals(finalPassword)) {
            if (user.getUserStatus().equals("1")) {
                request.getSession().setAttribute(Constant.USER, user);
                user.setPassword("");
                return CommonResult.success(user);
            }else {
                return CommonResult.failed("账户未激活");
            }
        }
        return CommonResult.failed("密码错误");
    }

    @Override
    public Object addUser(User user) {
        User dbUser = userService.findByAccount(user.getAccount());
        if (dbUser != null) {
            return CommonResult.failed("账号已被注册");
        }
        user.setUserStatus("0");
        user.setPassword(MD5Utils.encrpty(user.getPassword()));
        Integer r = userService.addUser(user);
        if (r > 0) {
            return CommonResult.success("注册成功");
        }else {
            return CommonResult.failed("注册失败");
        }
    }
}
