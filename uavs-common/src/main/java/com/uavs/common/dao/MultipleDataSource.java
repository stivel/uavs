package com.uavs.common.dao;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class MultipleDataSource extends AbstractRoutingDataSource{

	private static final ThreadLocal<String> dataSourceKey = new InheritableThreadLocal<String>();

    public static void setDataSourceKey(String dataSource) {
    	dataSourceKey.set(dataSource);
    }
	public static void setReadDataSource(){
		dataSourceKey.set(DataSourceEnum.readDataSource.toString());
	}
    @Override
    protected Object determineCurrentLookupKey() {
        return dataSourceKey.get(); 
    }

}
