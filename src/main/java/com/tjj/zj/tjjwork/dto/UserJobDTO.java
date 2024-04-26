package com.tjj.zj.tjjwork.dto;

import com.tjj.zj.tjjwork.entity.Job;
import com.tjj.zj.tjjwork.entity.UserJob;
import lombok.Data;

import java.util.List;

/**
 * @parma
 */
@Data
public class UserJobDTO {

    private Job job;

    private List<UserJob> userJob;
}
