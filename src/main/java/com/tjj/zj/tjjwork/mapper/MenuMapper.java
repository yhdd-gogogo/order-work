package com.tjj.zj.tjjwork.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tjj.zj.tjjwork.entity.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @parma
 */
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {

    List<Menu> findMenuTree();

    Menu findMenuByCode(@Param("menuCode") String menuCode);

    Menu farMenu(@Param(("menuParCode")) String menuParCode);
}
