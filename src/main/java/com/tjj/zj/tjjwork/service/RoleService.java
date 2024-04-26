package com.tjj.zj.tjjwork.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tjj.zj.tjjwork.entity.Role;

import java.util.Map;

/**
 * @parma
 */
public interface RoleService {

    Object save(Role role);

    Page<Role> findByPage(Map<String, Object> params, Integer page, Integer size);

    Object setRole(String userId, String roleId);

    Object deleteRole(String roleId);
}
