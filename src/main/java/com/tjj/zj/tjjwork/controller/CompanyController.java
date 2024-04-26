package com.tjj.zj.tjjwork.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tjj.zj.tjjwork.entity.Company;
import com.tjj.zj.tjjwork.entity.User;
import com.tjj.zj.tjjwork.service.CompanyService;
import com.tjj.zj.tjjwork.web.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @parma
 */
@Api(tags = "企业管理")
@Slf4j
@RestController
@RequestMapping("/comp")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping("/findByPage")
    @ApiOperation("企业列表分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "当前页"),
            @ApiImplicitParam(name = "size", value = "每页显示记录条数"),
            @ApiImplicitParam(name = "params", value = "额外参数"),
            @ApiImplicitParam(name = "companyName", value = "企业名称"),
            @ApiImplicitParam(name = "status", value = "企业状态"),
    })
    public Object findByPage(@RequestParam Map<String, Object> params,
                             @RequestParam(defaultValue = "1") Integer page,
                             @RequestParam(defaultValue = "10") Integer size) {
        Page<Company> pageResult = companyService.findByPage(params, page, size);
        return CommonResult.success(pageResult);
    }

    @GetMapping("/check")
    @ApiOperation("管理员审批企业")
    public Object checkCompany(@RequestParam("companyId") String companyId) {
        return companyService.checkCompany(companyId);
    }

    @GetMapping("/binding")
    @ApiOperation("商户绑定企业")
    public Object bindingComp(@RequestParam("companyId") String companyId) {
        return companyService.bindingComp(companyId);
    }

    @GetMapping("detail")
    @ApiOperation("查看企业")
    public Object detailComp(@RequestParam("companyId") String companyId) {
        return companyService.detailComp(companyId);
    }

}
