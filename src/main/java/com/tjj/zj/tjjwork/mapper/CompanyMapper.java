package com.tjj.zj.tjjwork.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tjj.zj.tjjwork.entity.Company;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @parma
 */
@Mapper
public interface CompanyMapper extends BaseMapper<Company> {

    Page<Company> findByPage(Page<Company> page, @Param("params") Map<String, Object> params);

    Integer bindingComp(@Param("companyId") String companyId,@Param("userId") String userId);
}
