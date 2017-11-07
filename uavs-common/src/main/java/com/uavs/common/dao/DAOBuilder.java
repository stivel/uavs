package com.uavs.common.dao;

import com.uavs.common.constances.SysConstances;

public class DAOBuilder {
	/**
	 * 通过daoName初始化 MyBatisSqlDAO
	 * @param daoName
	 * @return MyBatisSqlDAO
	 */
	public SqlMapper initDAO(String daoName){
		String namespace=SysConstances.CFG_MAP.get("business.namespace")+"."+daoName;
		SqlMapper dao=new SqlMapper(namespace);
		return dao;
	}
	public SqlMapper initDAO(String namespace,String daoName){
		namespace=namespace+"."+daoName;
		SqlMapper dao=new SqlMapper(namespace);
		return dao;
	}
	
	/**
	 * 
	 * @param cls POJO对象
	 * @return
	*/
	public TableMapper initDAO(Class cls){
		String namespace=DAOBuilder.findNamespace(cls);
		TableMapper dao=new TableMapper(namespace);
		return dao;
	}
	/**
	 * 
	  * initDAO(无指定构造)
	  *
	  * @Title: initDAO
	  * @Description: TODO
	  * @param @return    
	  * @return TableMapper    
	  * @throws
	 */
	public TableDefaultMapper initDAO(){
		TableDefaultMapper dao=new TableDefaultMapper();
		return dao;
	}
	 
	/**
	 * 
	  * findNamespace(获得namespace)
	  *
	  * @Title: findNamespace
	  * @Description: TODO
	  * @param @param cls
	  * @param @return    
	  * @return String    
	  * @throws
	 */
	public static String findNamespace(Class cls){
		String name=cls.getName();
		String head=name.split(".entity.")[0];
		String className=name.substring(name.lastIndexOf("."),name.length());
		if(className.length()>=3&&className.subSequence(className.length()-3, className.length()).equals("Key")){//xxxPOKey
			className=className.substring(0, className.length()-3);
		}
		if(className.length()>=7&&className.subSequence(className.length()-7, className.length()).equals("Example")){//xxxPOExample
			className=className.substring(0, className.length()-7);
		}
		if(className.length()>=9&&className.subSequence(className.length()-9, className.length()).equals("WithBLOBs")){//xxxWithBLOBs
			className=className.substring(0, className.length()-9);
		}
		String namespace=head+".mapper"+className+"Mapper.";
		return namespace;
	}
	public static void  main(String argus[]){
		//System.out.println(DAOBuilder.findNamespace(EcCarouselPO.class));
		//System.out.println(DAOBuilder.findNamespace(EcCarouselPOExample.class));
		//System.out.println(DAOBuilder.findNamespace(AdPrivilegeGroupRefPOKey.class));
	}
	
}
