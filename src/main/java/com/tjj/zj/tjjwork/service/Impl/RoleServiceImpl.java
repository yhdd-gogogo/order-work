package com.tjj.zj.tjjwork.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tjj.zj.tjjwork.entity.Job;
import com.tjj.zj.tjjwork.entity.Menu;
import com.tjj.zj.tjjwork.entity.Role;
import com.tjj.zj.tjjwork.mapper.MenuMapper;
import com.tjj.zj.tjjwork.mapper.RoleMapper;
import com.tjj.zj.tjjwork.service.RoleService;
import com.tjj.zj.tjjwork.util.ModelTree.TreeNode;
import com.tjj.zj.tjjwork.util.ModelTree.TreeUtils;
import com.tjj.zj.tjjwork.util.SimpleKeyUtil;
import com.tjj.zj.tjjwork.web.CommonResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @parma
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private MenuMapper menuMapper;


    @Override
    @Transactional
    public Object save(Role role) {
        String roleId = SimpleKeyUtil.genShortUuId();
        boolean roleExist = roleExist(roleId);
        if (roleExist) {
            role.setRoleId(roleId);
            role.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            roleMapper.add(role);
            List<TreeNode> roleMenus = role.getMenus();
            List<Menu> menus = TreeUtils.flatTree(roleMenus, Menu.class);
            List<String> menuCodes = new ArrayList<>();
            for (Menu menu : menus) {
                String menuParCode = menu.getMenuParCode();
                if (menuParCode.equals("0")) { //根菜单
                    if (!menuCodes.contains(menu.getMenuCode())) {
                        menuCodes.add(menu.getMenuCode());
                    }
                }else { //不是根菜单
                    if (!menuCodes.contains(menu.getMenuCode())) {
                        menuCodes.add(menu.getMenuCode());
                    }
                    if (!menuCodes.contains(menuParCode)) {
                        menuCodes.add(menuParCode);
                    }
                    Menu farMenu = menuMapper.farMenu(menuParCode);
                    String parCode = farMenu.getMenuParCode();
                    if (parCode.equals("0")) {
                        if (!menuCodes.contains(farMenu.getMenuCode())) {
                            menuCodes.add(farMenu.getMenuCode());
                        }
                    }else {
                        if (!menuCodes.contains(parCode)) {
                            menuCodes.add(parCode);
                        }
                        if (!menuCodes.contains(farMenu.getMenuCode())) {
                            menuCodes.add(farMenu.getMenuCode());
                        }
                    }
                }
            }
            for (String menuCode : menuCodes) {
                roleMapper.roleMenu(roleId,menuCode);
            }
            return CommonResult.success("添加角色成功");
        }else {
            save(role);
        }
        return CommonResult.success("添加角色成功");
    }

    @Override
    public Page<Role> findByPage(Map<String, Object> params, Integer page, Integer size) {
        Page<Role> rolePage = roleMapper.findByPage(new Page<>(page,size),params);
        return rolePage;
    }

    @Override
    public Object setRole(String userId, String roleId) {
        roleMapper.setRole(userId,roleId);
        return CommonResult.success("设置角色成功");
    }

    @Override
    public Object deleteRole(String roleId) {
        roleMapper.deleteRole(roleId);
        return CommonResult.success("删除角色成功");
    }

    public boolean roleExist(String roleId) {
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id",roleId);
        Role exist = roleMapper.selectOne(queryWrapper);
        if (Objects.isNull(exist)) {
            return true;
        }else {
            return false;
        }
    }

}
