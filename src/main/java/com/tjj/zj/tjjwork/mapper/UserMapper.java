package com.tjj.zj.tjjwork.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tjj.zj.tjjwork.dto.RoleMenuDTO;
import com.tjj.zj.tjjwork.dto.UserRoleDTO;
import com.tjj.zj.tjjwork.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @parma
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    Integer addUser(User user);

    Page<User> findByPage(Page<User> page,@Param("params")  Map<String, Object> params);

    UserRoleDTO userRoleMap(@Param(("userId")) String userId);

    List<RoleMenuDTO> roleMenuMap(@Param(("roleId")) String roleId);
}
