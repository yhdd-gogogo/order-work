package com.tjj.zj.tjjwork.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tjj.zj.tjjwork.entity.User;

import java.util.Map;

/**
 * @parma
 */
public interface UserService {

    User findByAccount(String account);

    Integer addUser(User user);

    Page<User> findByPage(Map<String, Object> params, Integer page, Integer size);

    Object freezeUser(String accountId);

    Object validUser(String accountId);

    Object detailUser(String userId);
}
