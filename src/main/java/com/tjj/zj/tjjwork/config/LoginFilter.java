package com.tjj.zj.tjjwork.config;

import com.alibaba.fastjson.JSONObject;

import com.tjj.zj.tjjwork.util.Constant;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @parma
 */
@Slf4j
//@WebFilter(urlPatterns = "/*") //拦截所有请求
public class LoginFilter implements Filter {


    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String path = request.getServletPath();
        List allowPathList = new ArrayList();
        //需要放行的页面
        allowPathList.add("/login");
        if (!allowPathList.contains(path)) {
            HttpSession session = request.getSession(false);
            if (Objects.isNull(session)) {
                response.getWriter()
                        .append(new JSONObject()
                                .fluentPut("msg", "ILLEGAL REQUEST,NOLOGIN")
                                .fluentPut("success", "false")
                                .fluentPut("code", "500")
                                .toString()
                        );
                return;
            }
            if (Objects.isNull(session.getAttribute(Constant.USER))) {
                response.getWriter()
                        .append(new JSONObject()
                                .fluentPut("msg", "ILLEGAL REQUEST,NOLOGIN")
                                .fluentPut("success", "false")
                                .fluentPut("code", "500")
                                .toString()
                        );
                return;
            }
        }
        chain.doFilter(request, response);//放行请求
        return;//结束当前方法的执行

    }
}
