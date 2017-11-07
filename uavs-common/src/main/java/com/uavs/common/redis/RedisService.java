package com.uavs.common.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface RedisService {
	
	/**
	* @Description
	* @param key
	* @param value 
	* @author shiyang
	* @version 1.0.0
	*/
	public   void setValue(String key,Object value);

	/**
	 * @Description redis list存放多值
	 * @param key
	 * @param values 序列化后的list对象
	 * @param seconds 过期时间 秒
	 * @author liyanqiang
	 * 上午 9:32:44
	 * @version 1.0.0
	 */
	public void setList(String key, List<String> values, Long seconds);

	/**
	 * @Description redis left压栈单个值
	 * @param key
	 * @param value 序列化后的对象
	 * @param seconds 过期时间 秒
	 * @author liyanqiang
	 * 上午 9:32:44
	 * @version 1.0.0
	 */
	public void leftPushToList(String key, String value, Long seconds);

	/**
	 * @Description redis right压栈单个值
	 * @param key
	 * @param value 序列化后的对象
	 * @param seconds 过期时间 秒
	 * @author liyanqiang
	 * 上午 9:32:44
	 * @version 1.0.0
	 */
	public void rightPushToList(String key, String value, Long seconds);

	/**
	 * @Description redis 删除list里的某个元素
	 * @param key
	 * @param count count > 0 : 从表头开始向表尾搜索，移除与 VALUE 相等的元素，数量为 COUNT 。
	 * count < 0 : 从表尾开始向表头搜索，移除与 VALUE 相等的元素，数量为 COUNT 的绝对值。
	 * @param value 要删除的值
	 * @author liyanqiang
	 * 上午 9:32:44
	 * @version 1.0.0
	 */
	public void removeFromList(String key,long count, String value);


	/**
	 * @Description redis set存放多个成员
	 * @param key
	 * @param values 序列化后的list对象
	 * @param seconds 过期时间 秒
	 * @author liyanqiang
	 * 上午 9:32:44
	 * @version 1.0.0
	 */
	public void setSetValue(String key, Set<String> values, Long seconds);

	/**
	 * @Description redis set存放单个成员
	 * @param key
	 * @param value 序列化后的对象
	 * @param seconds 过期时间 秒
	 * @author liyanqiang
	 * 上午 9:32:44
	 * @version 1.0.0
	 */
	public void setSetOneValue(String key, String value, Long seconds);

	/**
	 * @Description redis 删除指定元素
	 * @param key
	 * @param value 要删除的值
	 * @author liyanqiang
	 * 上午 9:32:44
	 * @version 1.0.0
	 */
	public void removeSetValue(String key, String value);

	/**
	 * @Description redis SortSet 操作 排序 多成员 也可但成员
	 * @param key
	 * @param map 序列化后的对象 map-key存储的成员 map-value用来排序的值，double类型
	 * @param seconds 过期时间 秒
	 * @author liyanqiang
	 * 上午 9:32:44
	 * @version 1.0.0
	 */
	public void setZSetValue(String key, Map<String, Double> map, Long seconds);

	/**
	 * @Description redis SortSet 删除成员
	 * @param key
	 * @param value 要删除的值
	 * @author liyanqiang
	 * 上午 9:32:44
	 * @version 1.0.0
	 */
	public void removeZSetValue(String key, String value);

	/**
	 * @Description redis Hash 操作
	 * string类型的field和value的映射表
	 * 用于存储对象
	 * @param key
	 * @param map 序列化后的对象map
	 * @param seconds 过期时间 秒
	 * @author liyanqiang
	 * 上午 9:32:44
	 * @version 1.0.0
	 */
	public void setHash(String key, Map<String, String> map, Long seconds);
	
 
	/**
	* @Description
	* @param key
	* @param value
	* @param seconds 过期时间一秒为单位
	* @author shiyang
	* @version 1.0.0
	*/
	public void setValue(String key,Object value,Long seconds);
	
	/**
	* @Description 获取redis 中值
	* @param key
	* @return
	* @author shiyang
	* @version 1.0.0
	*/
	public <M> M getValue(String key);
	
	
	/**
	* @Description 删除对应的key
	* @param key
	* @author shiyang
	* @version 1.0.0
	*/
	public   void delKey(String key);



	/**
	 * @Description 通过key 获取value list
	 * @param key
	 * @param start list 起始位置
	 * @param end 结束位置
	 * @return 根据存放对象接收
	 * @author liyanqiang
	 * 上午9:33:50
	 * @version 1.0.0
	 */
	public List<String> getList(String key, long start, long end);

	/**
	 * @Description 通过key 获取value set
	 * @param key
	 * @return 根据存放对象接收
	 * @author liyanqiang
	 * 上午9:33:50
	 * @version 1.0.0
	 */
	public Set<String> getSetValues(String key);

	/**
	 * @Description 通过key 获取value 排序set
	 * @param key
	 * @param start list 起始位置
	 * @param end 结束位置
	 * @return 根据存放对象接收
	 * @author liyanqiang
	 * 上午9:33:50
	 * @version 1.0.0
	 */
	public Set<String> getZSet(String key, long start, long end);

	/**
	 * @Description 通过key 获取对象
	 * @param key
	 * @return 根据存放对象接收
	 * @author liyanqiang
	 * 上午9:33:50
	 * @version 1.0.0
	 */
	public Map<String, String> getHash(String key);

	/**
	 * @Description 通过key 进行模糊查询
	 * 当数据规模较大时使用，会严重影响Redis性能
	 * @param key  key*
	 * @return 根据存放对象接收
	 * @author shiyang
	 * 上午9:33:50
	 * @version 1.0.0
	 */
	public Set<String> getValues(String key);
	
	/**
	 * @description 获取key失效时间
	 * @param key
	 * @return
	 * @author shiyang
	 * 2016年12月28日 下午8:26:55
	 */
	public Long getExpire(String key);
	/**
	 * @description 判断key是否存在
     * @param key
     * @return
     */
	public boolean exists(final String key);

}
