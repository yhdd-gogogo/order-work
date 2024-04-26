package com.tjj.zj.tjjwork.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tjj.zj.tjjwork.dto.RoleMenuDTO;
import com.tjj.zj.tjjwork.dto.UserRoleDTO;
import com.tjj.zj.tjjwork.entity.Menu;
import com.tjj.zj.tjjwork.entity.User;
import com.tjj.zj.tjjwork.mapper.MenuMapper;
import com.tjj.zj.tjjwork.mapper.UserMapper;
import com.tjj.zj.tjjwork.service.MenuService;
import com.tjj.zj.tjjwork.util.Constant;
import com.tjj.zj.tjjwork.util.ModelTree.TreeNode;
import com.tjj.zj.tjjwork.util.ModelTree.TreeUtils;
import com.tjj.zj.tjjwork.util.RequestHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @parma
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private UserMapper userMapper;


    @Override
    public TreeNode findMenuTree() {
        List<Menu> menus = menuMapper.findMenuTree();
        TreeNode treeNode = TreeUtils.buildTree(menus, Constant.MENU_ROOT_ID);
        return treeNode;
    }

    @Override
    public TreeNode userMenu() {
//        User user = (User) RequestHolder.getRequest().getSession().getAttribute(Constant.USER);
        String loginId = RequestHolder.getRequest().getHeader("userId");
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",loginId);
        User user = userMapper.selectOne(queryWrapper);
        UserRoleDTO userRoleDTO = userMapper.userRoleMap(user.getUserId());
        List<RoleMenuDTO> roleMenuDTO = userMapper.roleMenuMap(userRoleDTO.getRoleId());
        List<Menu> menus = new ArrayList<>();
        for (RoleMenuDTO menuDTO : roleMenuDTO) {
            String menuCode = menuDTO.getMenuCode();
            Menu menu = menuMapper.findMenuByCode(menuCode);
            menus.add(menu);
        }
        TreeNode treeNode = TreeUtils.buildTree(menus, Constant.MENU_ROOT_ID);
        return treeNode;
    }
}
