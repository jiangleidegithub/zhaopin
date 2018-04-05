package com.jianglei.zhaopin.dao;

import com.jianglei.zhaopin.model.Company;
import com.jianglei.zhaopin.query.CompanyQuery;


public interface CompanyMapper extends BaseDao<CompanyQuery, Company> {
	Company selecComanyAndJobsbyId(Integer id);
}
