package com.tjj.zj.tjjwork.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tjj.zj.tjjwork.entity.Company;
import com.tjj.zj.tjjwork.entity.User;
import com.tjj.zj.tjjwork.mapper.CompanyMapper;
import com.tjj.zj.tjjwork.mapper.UserMapper;
import com.tjj.zj.tjjwork.service.CompanyService;
import com.tjj.zj.tjjwork.util.Constant;
import com.tjj.zj.tjjwork.util.RequestHolder;
import com.tjj.zj.tjjwork.web.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @parma
 */
@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyMapper companyMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public Page<Company> findByPage(Map<String, Object> params, Integer page, Integer size) {
        return companyMapper.findByPage(new Page<>(page, size), params);
    }

    @Override
    public Object checkCompany(String companyId) {
        Company company = new Company();
        UpdateWrapper<Company> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("status", "1")
                .eq("company_id", companyId);
        int update = companyMapper.update(company, updateWrapper);
        if (update > 0) {
            return CommonResult.success("审批成功");
        } else {
            return CommonResult.failed("审批失败");
        }
    }

    @Override
    public Object bindingComp(String companyId) {
//        User user = (User) RequestHolder.getRequest().getSession().getAttribute(Constant.USER);
        String loginId = RequestHolder.getRequest().getHeader("userId");
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",loginId);
        User user = userMapper.selectOne(queryWrapper);
        Integer update = companyMapper.bindingComp(companyId, user.getUserId());
        if (update > 0) {
            return CommonResult.success("绑定成功");
        } else {
            return CommonResult.failed("绑定失败");
        }

    }

    @Override
    public Object detailComp(String companyId) {

        QueryWrapper<Company> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("company_id", companyId);
        Company company = companyMapper.selectOne(queryWrapper);
        return CommonResult.success(company);

    }
}
