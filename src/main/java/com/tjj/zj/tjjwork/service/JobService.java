package com.tjj.zj.tjjwork.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tjj.zj.tjjwork.entity.FeedBack;
import com.tjj.zj.tjjwork.entity.Job;
import com.tjj.zj.tjjwork.entity.UserJob;

import java.util.List;
import java.util.Map;

/**
 * @parma
 */
public interface JobService {

    Page<Job> findByPage(Map<String, Object> params, Integer page, Integer size);

    Object checkJob(String jobId,String isAgree);

    Object issueJob(Job job);

    Object deleteJob(List<String> jobIds);

    Object detailJob(String jobId);

    Object resumeUser(String userId);

    Object applyJob(UserJob userJob);

    Object approverUser(String userId,String jobId, String isAgree);

    Page<UserJob> applyList(Integer page, Integer size);

    Object feedbackJob(FeedBack feedBack);

    Page<FeedBack> feedbackList(Map<String, Object> params, Integer page, Integer size);

    Object dealFeed(FeedBack feedBack);
}
