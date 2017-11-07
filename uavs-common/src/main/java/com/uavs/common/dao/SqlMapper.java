package com.uavs.common.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.uavs.common.page.DataPager;
import com.uavs.common.page.Pager;
import com.uavs.common.util.SpringBeanUtil;
import com.github.pagehelper.PageInfo;

public class SqlMapper {
	private String namespace;
	private MyBatisDaoSupport myBatisDaoSupport;
	public SqlMapper(String namespace){
		this.namespace = namespace;
		this.myBatisDaoSupport = SpringBeanUtil.getBean("myBatisDaoSupport", MyBatisDaoSupport.class);
	}
	
	public int insert(String insertId, Object parameter) {
		insertId=namespace+"."+insertId;
		return myBatisDaoSupport.insert(insertId, parameter);
	}

	public int update(String updateId, Object parameter) {
		updateId=namespace+"."+updateId;
		return myBatisDaoSupport.update(updateId, parameter);
	}

	public int delete(String deleteId, Object parameter) {
		deleteId=namespace+"."+deleteId;
		return myBatisDaoSupport.delete(deleteId, parameter);
	}

	public <M> M selectOne(String selectId, Object parameter) {
		selectId=namespace+"."+selectId;
		return myBatisDaoSupport.selectOne(selectId, parameter);
	}

	public <E> List<E> selectList(String selectId, Object parameter) {
		selectId=namespace+"."+selectId;
		return myBatisDaoSupport.selectList(selectId, parameter);
	}
	
	public<M> M  selectFirstFromList(String selectId, Object parameter) {
		selectId=namespace+"."+selectId;
		List<M> ls=myBatisDaoSupport.selectList(selectId, parameter);
		if(ls.size()>0){
			return ls.get(0);
		}
		return null;
	}
	
	public <E> List<E> selectLimitedList(String selectId,Object parameter, RowBounds rowBounds) {
		selectId=namespace+"."+selectId;

		return myBatisDaoSupport.selectLimitedList(selectId, parameter, rowBounds);
	}	

	public int selectPageCount(String countId, Object parameter) {
		countId=namespace+"."+countId;
		return myBatisDaoSupport.selectOne(countId, parameter);
	}
	
	public <E> DataPager<E> selectByPage(String selectId, Object params,Pager pager) {
		DataPager<E> dataPager=new DataPager<E>(pager);
		selectId=namespace+"."+selectId;
		RowBounds rowBounds = new RowBounds(pager.getPage(), pager.getRows());
		List<E> results = myBatisDaoSupport.selectLimitedList(selectId, params, rowBounds);
		dataPager.setRows(results);
		PageInfo page = new PageInfo(results);
		dataPager.setTotal(page.getTotal());
		return dataPager;
	}
}
