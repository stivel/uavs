package com.uavs.common.log;

import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;

import com.alibaba.fastjson.JSONArray;

public class Log {
	private static final Logger log = Logger.getLogger(Log.class);
	
	/**
	 * @description 方法执行前
	 * @param point
	 * @author shiyang
	 * 2016年10月6日 
	 */
	public void before(JoinPoint point){
		 //此方法返回的是一个数组，数组中包括request以及ActionCofig等类对象
        Object[] args = point.getArgs();
        log.info("执行方法"+point.getSignature()+"目标参数列表：");
        if (args != null) {
            for (Object obj : args) {
            	log.info(obj + ","+ JSON.toJSONString(obj));
            }
        }
	}
	public void after(){
		log.info("after");
		 
	}
	//有参并有返回值的方法
    public void afterReturning(JoinPoint point, Object returnObj) {
        //此方法返回的是一个数组，数组中包括request以及ActionCofig等类对象
        Object[] args = point.getArgs();
        if (args != null) {
        	log.info("方法名为"+point.getTarget()+
        	        point.getSignature().getName()+"的目标参数列表：");
            
            if(returnObj!=null){
            	log.info("执行结果是：" + JSONArray.toJSON(returnObj));
            }
        }
    }

	/**
	 * @description 异常后执行
	 * @param e
	 * @author shiyang
	 * 2016年10月6日 
	 */
	public void handlerException(Throwable e) {
		log.info("handingException......" +e.toString());
		e.printStackTrace();
	}
 
}
