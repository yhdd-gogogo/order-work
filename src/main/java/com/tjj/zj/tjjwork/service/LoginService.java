package com.tjj.zj.tjjwork.service;

import com.tjj.zj.tjjwork.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.NoSuchAlgorithmException;

/**
 * @parma
 */
public interface LoginService {

    Object login(String account, String password, HttpServletRequest request) ;

    Object addUser(User user);
}
