package com.jianglei.zhaopin.dao;

public interface BaseDao<Q, T> {

	T selectByPrimaryKey(Integer id);

	void deleteByPrimaryKey(Integer id);

	void insert(T t);

	void insertSelective(T t);

	void updateByPrimaryKeySelective(T t);

	void updateByPrimaryKey(T t);
}
