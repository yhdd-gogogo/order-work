package com.tjj.zj.tjjwork.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tjj.zj.tjjwork.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @parma
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {

    void add(Role role);

    Page<Role> findByPage(Page<Role> page,@Param("params") Map<String, Object> params);

    void roleMenu(@Param("roleId")String roleId,@Param(("menuCode")) String menuCode);

    void setRole(@Param("userId") String userId,@Param("roleId") String roleId);

    void deleteRole(@Param("roleId") String roleId);
}
