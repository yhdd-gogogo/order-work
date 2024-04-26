package com.tjj.zj.tjjwork.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tjj.zj.tjjwork.entity.User;
import com.tjj.zj.tjjwork.mapper.UserMapper;
import com.tjj.zj.tjjwork.service.UserService;
import com.tjj.zj.tjjwork.util.Constant;
import com.tjj.zj.tjjwork.util.RequestHolder;
import com.tjj.zj.tjjwork.web.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @parma
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findByAccount(String account) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account", account);
        User user = userMapper.selectOne(queryWrapper);
        return user;
    }

    @Override
    public Integer addUser(User user) {
        return userMapper.addUser(user);
    }

    @Override
    public Page<User> findByPage(Map<String, Object> params, Integer page, Integer size) {
        return userMapper.findByPage(new Page<>(page, size), params);
    }

    @Override
    public Object freezeUser(String accountId) {
        User user = new User();
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("user_status", "0")
                .eq("account", accountId);
        int update = userMapper.update(user, updateWrapper);
        if (update > 0) {
            return CommonResult.success("冻结成功");
        } else {
            return CommonResult.failed("冻结失败");
        }

    }

    @Override
    public Object validUser(String accountId) {
        User user = new User();
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("user_status", "1")
                .eq("account", accountId);
        int update = userMapper.update(user, updateWrapper);
        if (update > 0) {
            return CommonResult.success("激活/解冻成功");
        } else {
            return CommonResult.failed("激活/解冻失败");
        }
    }

    @Override
    public Object detailUser(String userId) {

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        User user = userMapper.selectOne(queryWrapper);
        return CommonResult.success(user);

    }
}
