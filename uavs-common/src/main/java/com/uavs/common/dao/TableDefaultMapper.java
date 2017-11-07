package com.uavs.common.dao;

import java.util.List;

import org.apache.ibatis.binding.MapperMethod.ParamMap;
import org.apache.ibatis.session.RowBounds;

import com.uavs.common.page.DataPager;
import com.uavs.common.page.Pager;
import com.uavs.common.util.SpringBeanUtil;
import com.github.pagehelper.PageInfo;

public class TableDefaultMapper {
	private String namespace;
	private DataSourceSupport dataSourceSupport;
	public TableDefaultMapper(){
		this.dataSourceSupport=SpringBeanUtil.getBean("dataSourceSupport", DataSourceSupport.class);
	}
	
	public <E> List<E>  selectByExample(Object example){
		this.namespace=DAOBuilder.findNamespace(example.getClass());
		String selectId=namespace+ExampleEnum.selectByExample;
		List<E> ls=this.dataSourceSupport.selectList(selectId, example);
		return ls;
	}
	
	public <M> M selectFirstByExample(Object example){
		this.namespace=DAOBuilder.findNamespace(example.getClass());
		String selectId=namespace+ExampleEnum.selectByExample;
		List<M> ls=this.dataSourceSupport.selectList(selectId, example);
		if(ls.size()>0){
			return  ls.get(0);
		}
		return null;
	}
	
	public <M> M  selectByPrimaryKey(Class<M> cls,Object key){
		this.namespace=DAOBuilder.findNamespace(cls);
		String selectId=namespace+ExampleEnum.selectByPrimaryKey;
		M m=this.dataSourceSupport.selectOne(selectId, key);
		return m;
	}
	public <M> int deleteByPrimaryKey(Class<M> cls,Object key){
		
		this.namespace=DAOBuilder.findNamespace(cls);
		
		String deleteId=namespace+ExampleEnum.deleteByPrimaryKey;
		int count=this.dataSourceSupport.delete(deleteId, key);
		return count;
	}
	public int countByExample(Object example){
		this.namespace=DAOBuilder.findNamespace(example.getClass());
		
		String countId=namespace+ExampleEnum.countByExample;
		int count=this.dataSourceSupport.selectCount(countId, example);
		return count;
	}
	public int deleteByExample(Object example){
		this.namespace=DAOBuilder.findNamespace(example.getClass());
		
		String deleteId=namespace+ExampleEnum.deleteByExample;
		int count=this.dataSourceSupport.delete(deleteId, example);
		return count;
	}
	public int  insert(Object parameter){	
		this.namespace=DAOBuilder.findNamespace(parameter.getClass());
		
		String insertId=namespace+ExampleEnum.insert;
		int count=this.dataSourceSupport.insert(insertId, parameter);
		return count;
	}
	public int insertSelective(Object parameter){
		this.namespace=DAOBuilder.findNamespace(parameter.getClass());
		
		String insertId=namespace+ExampleEnum.insertSelective;
		int count=this.dataSourceSupport.insert(insertId, parameter);
		return count;
	}
	public int updateByExample(Object record, Object example) {
		this.namespace=DAOBuilder.findNamespace(record.getClass());
		
		ParamMap<Object> pm=new ParamMap<>();
		pm.put("record", record);
		pm.put("example", example);
		String updateId=namespace+ExampleEnum.updateByExample;
		int obj=this.dataSourceSupport.update(updateId, pm);
		return obj;
	}
	
	public int updateByExampleSelective(Object record,Object example){
		this.namespace=DAOBuilder.findNamespace(record.getClass());
		
		ParamMap<Object> pm=new ParamMap<>();
		pm.put("record", record);
		pm.put("example", example);
		String updateId=namespace+ExampleEnum.updateByExampleSelective;
		int obj=this.dataSourceSupport.update(updateId, pm);
		return obj;
	}
	
	public int updateByPrimaryKeySelective(Object record) {
		this.namespace=DAOBuilder.findNamespace(record.getClass());
		
		String updateId=namespace+ExampleEnum.updateByPrimaryKeySelective;
		int obj=this.dataSourceSupport.update(updateId, record);
		return obj;
	}
	public <M> int updateByPrimaryKey(Class<M> cls,Object key) {
		this.namespace=DAOBuilder.findNamespace(cls);
		
		String updateId=namespace+ExampleEnum.updateByPrimaryKey;
		int obj=this.dataSourceSupport.update(updateId, key);
		return obj;
	}
	
	public <E,M> List<E> selectLimitedList(M example, int offset,int limit){
		this.namespace=DAOBuilder.findNamespace(example.getClass());
		
		String selectId=namespace+ExampleEnum.selectByExample;
		RowBounds rowBounds = new RowBounds(offset, limit);
		List<E> ls=this.dataSourceSupport.selectLimitedList(selectId, example, rowBounds);
		return ls;
	}
	public <E,M> DataPager<E> selectByPage(Pager pager, M example) {
		this.namespace=DAOBuilder.findNamespace(example.getClass());
		
		DataPager<E> dataPager=new DataPager<E>(pager);
		RowBounds rowBounds = new RowBounds(pager.getPage(), pager.getRows());
		List<E> results=this.dataSourceSupport.selectLimitedList(namespace+ExampleEnum.selectByExample.toString(), example,rowBounds);
		dataPager.setRows(results);
		PageInfo page = new PageInfo(results);
		dataPager.setTotal(page.getTotal());
		return dataPager;
	}
}
