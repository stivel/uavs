package com.uavs.common.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Component;

@Component("myBatisDaoSupport")
public class MyBatisDaoSupport extends SqlSessionDaoSupport {

    /*@Resource  
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory){  
        super.setSqlSessionFactory(sqlSessionFactory);  
    } */ 
    
	public int insert(String insertId, Object parameter) {
		return getSqlSession().insert(insertId, parameter);
	}

	public int update(String updateId, Object parameter) {
		return getSqlSession().update(updateId, parameter);
	}
	
	public int delete(String deleteId, Object parameter) {
		return getSqlSession().delete(deleteId, parameter);
	}

	public <M> M selectOne(String selectId, Object parameter) {
		return getSqlSession().selectOne(selectId, parameter);
	}

	public <E> List<E> selectList(String selectId, Object parameter) {
		return getSqlSession().selectList(selectId, parameter);
	}

	public <E> List<E> selectLimitedList(String selectId,Object parameter, RowBounds rowBounds) {
		return getSqlSession().selectList(selectId, parameter,rowBounds);
	}

	public int selectPageCount(String countId, Object parameter) {
		return getSqlSession().selectOne(countId, parameter);
	}
}
