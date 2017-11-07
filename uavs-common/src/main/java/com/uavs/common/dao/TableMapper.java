package com.uavs.common.dao;

import java.util.List;

import org.apache.ibatis.binding.MapperMethod.ParamMap;
import org.apache.ibatis.session.RowBounds;

import com.uavs.common.page.DataPager;
import com.uavs.common.page.Pager;
import com.uavs.common.util.SpringBeanUtil;
import com.github.pagehelper.PageInfo;

public class TableMapper {
	private String namespace;
	private DataSourceSupport dataSourceSupport;
	public TableMapper(String namespace){
		this.namespace=namespace;
		this.dataSourceSupport=SpringBeanUtil.getBean("dataSourceSupport", DataSourceSupport.class);
	}
	
	public <E> List<E>  selectByExample(Object example){
		
		String selectId=namespace+ExampleEnum.selectByExample;
		List<E> ls=this.dataSourceSupport.selectList(selectId, example);
		return ls;
	}
	
	public <M> M selectFirstByExample(Object example){
		
		String selectId=namespace+ExampleEnum.selectByExample;
		List<M> ls=this.dataSourceSupport.selectList(selectId, example);
		if(ls.size()>0){
			return ls.get(0);
		}
		return null;
	}
	
public <E> List<E>  selectByExampleWithBLOBs(Object example){
		
		String selectId=namespace+ExampleEnum.selectByExampleWithBLOBs;
		List<E> ls=this.dataSourceSupport.selectList(selectId, example);
		return ls;
	}
	
	public <M> M selectFirstByExampleWithBLOBs(Object example){
		
		String selectId=namespace+ExampleEnum.selectByExampleWithBLOBs;
		List<M> ls=this.dataSourceSupport.selectList(selectId, example);
		if(ls.size()>0){
			return ls.get(0);
		}
		return null;
	}
	
	public <M> M  selectByPrimaryKey(Object key){
		String selectId=namespace+ExampleEnum.selectByPrimaryKey;
		M m=this.dataSourceSupport.selectOne(selectId, key);
		return m;
	}
	public int deleteByPrimaryKey(Object key){
		String deleteId=namespace+ExampleEnum.deleteByPrimaryKey;
		int count=this.dataSourceSupport.delete(deleteId, key);
		return count;
	}
	public int countByExample(Object example){
		String countId=namespace+ExampleEnum.countByExample;
		int count=this.dataSourceSupport.selectCount(countId, example);
		return count;
	}
	public int deleteByExample(Object example){
		String deleteId=namespace+ExampleEnum.deleteByExample;
		int count=this.dataSourceSupport.delete(deleteId, example);
		return count;
	}
	public int  insert(Object parameter){		
		String insertId=namespace+ExampleEnum.insert;
		int count=this.dataSourceSupport.insert(insertId, parameter);
		return count;
	}
	public int insertSelective(Object parameter){
		String insertId=namespace+ExampleEnum.insertSelective;
		int count=this.dataSourceSupport.insert(insertId, parameter);
		return count;
	}
	public int updateByExampleWithBLOBs(Object record, Object example) {
		ParamMap<Object> pm=new ParamMap<>();
		pm.put("record", record);
		pm.put("example", example);
		String updateId=namespace+ExampleEnum.updateByExampleWithBLOBs;
		int obj=this.dataSourceSupport.update(updateId, pm);
		return obj;
	}
	public int updateByExample(Object record, Object example) {
		ParamMap<Object> pm=new ParamMap<>();
		pm.put("record", record);
		pm.put("example", example);
		String updateId=namespace+ExampleEnum.updateByExample;
		int obj=this.dataSourceSupport.update(updateId, pm);
		return obj;
	}
	
	public int updateByExampleSelective(Object record,Object example){
		ParamMap<Object> pm=new ParamMap<>();
		pm.put("record", record);
		pm.put("example", example);
		String updateId=namespace+ExampleEnum.updateByExampleSelective;
		int obj=this.dataSourceSupport.update(updateId, pm);
		return obj;
	}
	
	public int updateByPrimaryKeySelective(Object record) {
		String updateId=namespace+ExampleEnum.updateByPrimaryKeySelective;
		int obj=this.dataSourceSupport.update(updateId, record);
		return obj;
	}
	public int updateByPrimaryKeyWithBLOBs(Object key) {
		String updateId=namespace+ExampleEnum.updateByPrimaryKeyWithBLOBs;
		int obj=this.dataSourceSupport.update(updateId, key);
		return obj;
	}
	
	public int updateByPrimaryKey(Object key) {
		String updateId=namespace+ExampleEnum.updateByPrimaryKey;
		int obj=this.dataSourceSupport.update(updateId, key);
		return obj;
	}
	
	public <E> List<E> selectLimitedList(Object parameter, int offset,int limit){
		String selectId=namespace+ExampleEnum.selectLimitedList;
		RowBounds rowBounds = new RowBounds(offset, limit);
		List<E> ls=this.dataSourceSupport.selectLimitedList(selectId, parameter, rowBounds);
		return ls;
	}
	public <E, M> DataPager<E> selectByPage(Pager pager, M example) {
		DataPager<E> dataPager=new DataPager<E>(pager);
		RowBounds rowBounds = new RowBounds(pager.getPage(), pager.getRows());
		List<E> results=this.dataSourceSupport.selectLimitedList(namespace+ExampleEnum.selectByExample.toString(), example,rowBounds);
		dataPager.setRows(results);
		PageInfo page = new PageInfo(results);
		dataPager.setTotal(page.getTotal());
		return dataPager;
	}
	public <E, M> DataPager<E> selectByPageWithBLOBs(Pager pager, M example) {
		DataPager<E> dataPager=new DataPager<E>(pager);
		RowBounds rowBounds = new RowBounds(pager.getPage(), pager.getRows());
		List<E> results=this.dataSourceSupport.selectLimitedList(namespace+ExampleEnum.selectByExampleWithBLOBs.toString(), example,rowBounds);
		dataPager.setRows(results);
		PageInfo page = new PageInfo(results);
		dataPager.setTotal(page.getTotal());
		return dataPager;
	}
}
