package com.tjj.zj.tjjwork.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tjj.zj.tjjwork.entity.Company;
import com.tjj.zj.tjjwork.entity.FeedBack;
import com.tjj.zj.tjjwork.entity.Job;
import com.tjj.zj.tjjwork.entity.UserJob;
import com.tjj.zj.tjjwork.service.JobService;
import com.tjj.zj.tjjwork.web.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @parma
 */
@Api(tags = "岗位管理")
@Slf4j
@RestController
@RequestMapping("/job")
public class JobController {

    @Autowired
    private JobService jobService;

    @GetMapping("/findByPage")
    @ApiOperation("岗位列表分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "当前页"),
            @ApiImplicitParam(name = "size", value = "每页显示记录条数"),
            @ApiImplicitParam(name = "params", value = "额外参数"),
            @ApiImplicitParam(name = "payWay", value = "结算方式"),
            @ApiImplicitParam(name = "sex", value = "性别要求"),
            @ApiImplicitParam(name = "jobType", value = "职位类别"),
            @ApiImplicitParam(name = "jobName", value = "岗位名称")
    })
    public Object findByPage(@RequestParam Map<String, Object> params,
                             @RequestParam(defaultValue = "1") Integer page,
                             @RequestParam(defaultValue = "10") Integer size) {
        Page<Job> pageResult = jobService.findByPage(params, page, size);
        return CommonResult.success(pageResult);
    }

    @GetMapping("/detail")
    @ApiOperation("查看岗位")
    public Object detailJob(@RequestParam("jobId") String jobId) {
        return jobService.detailJob(jobId);
    }

    @GetMapping("/check")
    @ApiOperation("管理员岗位审批")
    public Object checkJob(@RequestParam("jobId") String jobId,
                           @RequestParam("isAgree")String isAgree) {
        return jobService.checkJob(jobId,isAgree);
    }

    @PostMapping("/issue")
    @ApiOperation("商户发布兼职岗位")
    public Object issueJob(@RequestBody Job job) {
        return jobService.issueJob(job);
    }

    @PostMapping("/delete")
    @ApiOperation("商户删除兼职岗位")
    public Object deleteJob(@RequestBody List<String> jobIds) {
        return jobService.deleteJob(jobIds);
    }

    @GetMapping("/resume")
    @ApiOperation("商户查看报名者简历")
    public Object resumeUser(@RequestParam("userId") String userId) {
        return jobService.resumeUser(userId);
    }

    @PostMapping("/apply")
    @ApiOperation("岗位报名")
    public Object applyJob(@RequestBody UserJob userJob) {
        return jobService.applyJob(userJob);
    }

    @GetMapping("/applyList")
    @ApiOperation("用户查看自己兼职记录列表")
    public Object applyList(@RequestParam(defaultValue = "1") Integer page,
                            @RequestParam(defaultValue = "10") Integer size) {
        Page<UserJob> pageResult = jobService.applyList(page, size);
        return CommonResult.success(pageResult);
    }

    @GetMapping("/approver")
    @ApiOperation("商家审批报名者")
    public Object approverUser(@RequestParam("userId") String userId,
                               @RequestParam("jobId") String jobId,
                                @RequestParam("isAgree") String isAgree) {
        return jobService.approverUser(userId,jobId,isAgree);
    }

    @PostMapping("/feedback")
    @ApiOperation("兼职反馈")
    public Object feedbackJob(@RequestBody FeedBack feedBack) {
        return jobService.feedbackJob(feedBack);
    }

    @GetMapping("/feedbackList")
    @ApiOperation("反馈列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "当前页"),
            @ApiImplicitParam(name = "size", value = "每页显示记录条数"),
            @ApiImplicitParam(name = "params", value = "额外参数"),
            @ApiImplicitParam(name = "backStatus", value = "反馈状态")
    })
    public Object feedbackList(@RequestParam Map<String, Object> params,
                               @RequestParam(defaultValue = "1") Integer page,
                               @RequestParam(defaultValue = "10") Integer size) {
        Page<FeedBack> pageResult = jobService.feedbackList(params,page, size);
        return CommonResult.success(pageResult);
    }

    @PostMapping("/dealFeed")
    @ApiOperation("管理员操作反馈信息")
    public Object dealFeed(@RequestBody FeedBack feedBack) {
        return jobService.dealFeed(feedBack);
    }

}
