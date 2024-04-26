package com.tjj.zj.tjjwork.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tjj.zj.tjjwork.entity.Company;
import com.tjj.zj.tjjwork.entity.User;

import java.util.Map;

/**
 * @parma
 */
public interface CompanyService {

    Page<Company> findByPage(Map<String, Object> params, Integer page, Integer size);

    Object checkCompany(String companyId);

    Object bindingComp(String companyId);

    Object detailComp(String companyId);
}
