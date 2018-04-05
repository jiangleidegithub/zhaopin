package com.jianglei.zhaopin.dao;

import com.jianglei.zhaopin.model.Job;
import com.jianglei.zhaopin.query.JobQuery;

public interface JobMapper extends BaseDao<JobQuery, Job> {
	Job selectJobsAndCompanyById(Integer id);
}
