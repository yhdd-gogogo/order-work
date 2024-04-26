package com.tjj.zj.tjjwork.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tjj.zj.tjjwork.dto.*;
import com.tjj.zj.tjjwork.entity.*;
import com.tjj.zj.tjjwork.mapper.JobMapper;
import com.tjj.zj.tjjwork.mapper.UserMapper;
import com.tjj.zj.tjjwork.service.JobService;
import com.tjj.zj.tjjwork.util.Constant;
import com.tjj.zj.tjjwork.util.RequestHolder;
import com.tjj.zj.tjjwork.util.SimpleKeyUtil;
import com.tjj.zj.tjjwork.web.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.smartcardio.CommandAPDU;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @parma
 */
@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private JobMapper jobMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public Page<Job> findByPage(Map<String, Object> params, Integer page, Integer size) {
//        User user = (User) RequestHolder.getRequest().getSession().getAttribute(Constant.USER);
        String loginId = RequestHolder.getRequest().getHeader("userId");
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",loginId);
        User user = userMapper.selectOne(queryWrapper);
        String companyId = "";
        UserCompDTO dto = jobMapper.userCompMap(user.getUserId());
        if (Objects.nonNull(dto)) {
            companyId = dto.getCompanyId();
        }
        String userType = user.getUserType();
        Page<Job> jobPage;
        if (userType.equals("1")) { //个人用户
            jobPage = jobMapper.userFindByPage(new Page<>(page, size), params, user.getUserId());
        }else { //企业或管理员
            jobPage = jobMapper.findByPage(new Page<>(page, size), params, userType, companyId);
        }
        return jobPage;
    }


    @Override
    public Page<FeedBack> feedbackList(Map<String, Object> params, Integer page, Integer size) {
//        User user = (User) RequestHolder.getRequest().getSession().getAttribute(Constant.USER);
        String loginId = RequestHolder.getRequest().getHeader("userId");
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",loginId);
        User user = userMapper.selectOne(queryWrapper);
        String companyId = "";
        UserCompDTO dto = jobMapper.userCompMap(user.getUserId());
        if (Objects.nonNull(dto)) {
            companyId = dto.getCompanyId();
        }
        String userType = user.getUserType();
        Page<FeedBack> jobPage ;
        if (userType.equals("1")) { //个人用户
            jobPage = jobMapper.feedFindByPage(new Page<>(page,size),params,user.getUserId());
        }else { //企业或管理员
            jobPage = jobMapper.feedCompFindByPage(new Page<>(page,size),params,userType,companyId);
        }
        return jobPage;
    }

    @Override
    public Object dealFeed(FeedBack feedBack) {
//        User user = (User) RequestHolder.getRequest().getSession().getAttribute(Constant.USER);
        String loginId = RequestHolder.getRequest().getHeader("userId");
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",loginId);
        User user = userMapper.selectOne(queryWrapper);
        String userType = user.getUserType();
        if (userType.equals("0")) {
            feedBack.setDealTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            jobMapper.dealFeed(feedBack);
            return CommonResult.success("操作成功");
        }else {
            return CommonResult.failed("您没有权限这么做");
        }
    }

    @Override
    public Object checkJob(String jobId,String isAgree) {
        Job job = new Job();
        UpdateWrapper<Job> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("job_status", isAgree)
                .eq("job_id", jobId);
        int update = jobMapper.update(job, updateWrapper);
        CheckJobHisDTO jobHisDTO = new CheckJobHisDTO();
        jobHisDTO.setJobId(jobId);
        jobHisDTO.setOperation(isAgree);
        jobHisDTO.setOperationTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        jobMapper.checkJobHistory(jobHisDTO);
        if (update > 0) {
            return CommonResult.success("操作成功");
        } else {
            return CommonResult.failed("操作失败");
        }
    }

    @Override
    public Object issueJob(Job job) {
        String jobId = SimpleKeyUtil.genShortUuId();
        boolean jobExist = jobExist(jobId);
        if (jobExist) {
            job.setReleaseTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            job.setJobStatus("0");
            job.setJobId(jobId);
            Integer result = jobMapper.issueJob(job);
            if (result > 0) {
                return CommonResult.success("发布成功");
            } else {
                return CommonResult.failed("发布失败");
            }
        }else {
            issueJob(job);
        }
        return CommonResult.success("发布成功");

    }
    public boolean jobExist(String jobId) {
        QueryWrapper<Job> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("job_id",jobId);
        Job exist = jobMapper.selectOne(queryWrapper);
        if (Objects.isNull(exist)) {
            return true;
        }else {
            return false;
        }
    }

    @Transactional
    @Override
    public Object deleteJob(List<String> jobIds) {
        int r = 0;
        for (String jobId : jobIds) {
            Integer result = jobMapper.deleteJob(jobId);
            if (result > 0) {
                r = r + 1;
            }
        }
        return CommonResult.success("删除" + r + "条兼职信息成功");
    }

    @Override
    public Object detailJob(String jobId) {
        UserJobDTO userJobDTO = new UserJobDTO();
//        User user = (User) RequestHolder.getRequest().getSession().getAttribute(Constant.USER);
        String loginId = RequestHolder.getRequest().getHeader("userId");
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",loginId);
        User user = userMapper.selectOne(queryWrapper);
        String userType = user.getUserType();
        if (userType.equals("1")) { //个人用户
            QueryWrapper<Job> jobQueryWrapper = new QueryWrapper<>();
            jobQueryWrapper.eq("job_id", jobId);
            Job job = jobMapper.selectOne(jobQueryWrapper);
            return CommonResult.success(job);
        }else { //企业或管理员
            QueryWrapper<Job> jobQueryWrapper = new QueryWrapper<>();
            jobQueryWrapper.eq("job_id", jobId);
            Job job = jobMapper.selectOne(jobQueryWrapper);
            List<UserJob> userJob = jobMapper.userJob(jobId);
            userJobDTO.setJob(job);
            userJobDTO.setUserJob(userJob);
            return CommonResult.success(userJobDTO);
        }

    }

    @Override
    public Object resumeUser(String userId) {
        ResumeDTO resumeDTO = jobMapper.resumeUser(userId);
        return CommonResult.success(resumeDTO);
    }

    @Override
    public Object applyJob(UserJob userJob) {
//        User user = (User) RequestHolder.getRequest().getSession().getAttribute(Constant.USER);
        String loginId = RequestHolder.getRequest().getHeader("userId");
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",loginId);
        User user = userMapper.selectOne(queryWrapper);
        if (user.getUserType().equals("1")) { //个人用户
            userJob.setApplyTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            userJob.setWorkStatus("0");
            userJob.setUserId(user.getUserId());
            UserJob existJob = jobMapper.existJob(user.getUserId(),userJob.getJobId());
            if (Objects.nonNull(existJob) && !existJob.getWorkStatus().equals("2")) {
                //记录不为空且不是被拒绝的直接返回
                return CommonResult.failed("已经报过名了，无需再次报名");
            }
            //删除报名记录重新报名
            jobMapper.deleteApply(user.getUserId(),userJob.getJobId());
            Integer result = jobMapper.applyJob(userJob);
            if (result > 0) {
                return CommonResult.success("报名成功");
            }else {
                return CommonResult.failed("报名失败");
            }
        }else {
            return CommonResult.failed("不是学生用户，无法报名");
        }

    }

    @Override
    public Object approverUser(String userId,String jobId, String isAgree) {
//        User user = (User) RequestHolder.getRequest().getSession().getAttribute(Constant.USER);
        String loginId = RequestHolder.getRequest().getHeader("userId");
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",loginId);
        User user = userMapper.selectOne(queryWrapper);
        if (user.getUserType().equals("2")) { //企业用户
            Integer result = jobMapper.approverUser(userId,jobId,isAgree);
            if (result > 0 ) {
                return CommonResult.success("操作成功");
            }else {
                return CommonResult.failed("操作失败");
            }
        }else {
            return CommonResult.failed("暂无操作权限");
        }


    }

    @Override
    public Page<UserJob> applyList(Integer page, Integer size) {
//        User user = (User) RequestHolder.getRequest().getSession().getAttribute(Constant.USER);
        String loginId = RequestHolder.getRequest().getHeader("userId");
        Page<UserJob> userJobPage = jobMapper.applyList(new Page<>(page,size),loginId);
        return userJobPage;
    }

    @Override
    public Object feedbackJob(FeedBack feedBack) {
//        User user = (User) RequestHolder.getRequest().getSession().getAttribute(Constant.USER);
        String loginId = RequestHolder.getRequest().getHeader("userId");
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",loginId);
        User user = userMapper.selectOne(queryWrapper);
        feedBack.setStatus("0");
        feedBack.setUserId(loginId);
        feedBack.setUserName(user.getLoginName());
        feedBack.setPhoneNum(user.getPhoneNum());
        JobCompDTO jobCompDTO = jobMapper.jobComp(feedBack.getJobId(),loginId);
        feedBack.setCompanyId(jobCompDTO.getCompanyId());
        feedBack.setCompanyName(jobCompDTO.getCompanyName());
        feedBack.setJobName(jobCompDTO.getJobName());
        feedBack.setFeedbackTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        Integer result = jobMapper.feedbackJob(feedBack);
        if (result > 0) {
            return CommonResult.success("反馈成功");
        }else {
            return CommonResult.failed("反馈失败");
        }
    }




}
