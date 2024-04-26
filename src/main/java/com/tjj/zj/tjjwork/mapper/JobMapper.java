package com.tjj.zj.tjjwork.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tjj.zj.tjjwork.dto.*;
import com.tjj.zj.tjjwork.entity.FeedBack;
import com.tjj.zj.tjjwork.entity.Job;
import com.tjj.zj.tjjwork.entity.User;
import com.tjj.zj.tjjwork.entity.UserJob;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @parma
 */
@Mapper
public interface JobMapper extends BaseMapper<Job> {


    Page<Job> findByPage(Page<Job> page,@Param("params") Map<String, Object> params,@Param("userType") String userType,@Param("companyId") String companyId);

    Integer issueJob(Job job);

    Integer deleteJob(@Param("jobId") String jobId);

    UserCompDTO userCompMap(@Param("userId") String userId);

    List<UserJob> userJob(@Param(("jobId")) String jobId);

    ResumeDTO resumeUser(@Param("userId") String userId);

    Integer applyJob(UserJob userJob);

    UserJob existJob(@Param("userId") String userId, @Param("jobId") String jobId);

    Integer approverUser(@Param("userId") String userId, @Param("jobId")String jobId,@Param("isAgree")String isAgree);

    Page<Job> userFindByPage(Page<Job> page,@Param("params") Map<String, Object> params,@Param("userId") String userId);

    Page<UserJob> applyList(Page<UserJob> page, @Param("userId") String userId);

    void deleteApply(@Param("userId") String userId, @Param("jobId") String jobId);

    void checkJobHistory(CheckJobHisDTO jobHisDTO);

    JobCompDTO jobComp(@Param(("jobId")) String jobId,@Param(("userId")) String userId);

    Integer feedbackJob(FeedBack feedBack);

    Page<FeedBack> feedFindByPage(Page<FeedBack> page,@Param("params") Map<String, Object> params,@Param("userId") String userId);

    Page<FeedBack> feedCompFindByPage(Page<FeedBack> page, @Param("params") Map<String, Object> params, @Param("userType")String userType, @Param("companyId")String companyId);

    void dealFeed(FeedBack feedBack);
}
