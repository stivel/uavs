package com.uavs.common.redis;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.StringUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

@Service
public class RedisServiceImple implements RedisService {
	private RedisTemplate  redisTemplate;

	/**
	* @Description redis存放 
	* @param key
	* @param value 序列化后的对象
	* @author shiyang
	* 上午9:32:44
	* @version 1.0.0
	*/
	@Override
	public void setValue(String key, Object value) {
		ValueOperations<String, Object> opsForValue = redisTemplate.opsForValue();
		opsForValue.set(key, value);
		
	}

	/**
	 * @Description redis list存放多值
	 * @param key
	 * @param values 序列化后的list对象
	 * @param seconds 过期时间 秒
	 * @author liyanqiang
	 * 上午 9:32:44
	 * @version 1.0.0
	 */
	public void setList(String key, List<String> values, Long seconds) {
		ListOperations<String, String> lop = redisTemplate.opsForList();
		for (String value : values) {
			lop.leftPush(key, value);
		}
		setExpire(key, seconds);
	}

	/**
	 * @Description redis left压栈单个值
	 * @param key
	 * @param value 序列化后的对象
	 * @param seconds 过期时间 秒
	 * @author liyanqiang
	 * 上午 9:32:44
	 * @version 1.0.0
	 */
	public void leftPushToList(String key, String value, Long seconds) {
		ListOperations<String, String> lop = redisTemplate.opsForList();
		lop.leftPush(key, value);
		setExpire(key, seconds);
	}

	/**
	 * @Description redis right压栈单个值
	 * @param key
	 * @param value 序列化后的对象
	 * @param seconds 过期时间 秒
	 * @author liyanqiang
	 * 上午 9:32:44
	 * @version 1.0.0
	 */
	public void rightPushToList(String key, String value, Long seconds) {
		ListOperations<String, String> lop = redisTemplate.opsForList();
		lop.rightPush(key, value);
		setExpire(key, seconds);
	}

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
	public void removeFromList(String key,long count, String value) {
		ListOperations<String, String> lop = redisTemplate.opsForList();
		lop.remove(key, count, value);
	}


	/**
	 * @Description redis set存放多个成员
	 * @param key
	 * @param values 序列化后的list对象
	 * @param seconds 过期时间 秒
	 * @author liyanqiang
	 * 上午 9:32:44
	 * @version 1.0.0
	 */
	public void setSetValue(String key, Set<String> values, Long seconds) {
		SetOperations<String, String> sop = redisTemplate.opsForSet();
		for (String value : values) {
			sop.add(key, value);
		}
		setExpire(key, seconds);
	}

	/**
	 * @Description redis set存放单个成员
	 * @param key
	 * @param value 序列化后的对象
	 * @param seconds 过期时间 秒
	 * @author liyanqiang
	 * 上午 9:32:44
	 * @version 1.0.0
	 */
	public void setSetOneValue(String key, String value, Long seconds) {
		SetOperations<String, String> sop = redisTemplate.opsForSet();
		sop.add(key, value);
		setExpire(key, seconds);
	}

	/**
	 * @Description redis 删除指定元素
	 * @param key
	 * @param value 要删除的值
	 * @author liyanqiang
	 * 上午 9:32:44
	 * @version 1.0.0
	 */
	public void removeSetValue(String key, String value) {
		SetOperations<String, String> sop = redisTemplate.opsForSet();
		sop.remove(key, value);
	}

	/**
	 * @Description redis SortSet 操作 排序 多成员 也可但成员
	 * @param key
	 * @param map 序列化后的对象 map-key存储的成员 map-value用来排序的值，double类型
	 * @param seconds 过期时间 秒
	 * @author liyanqiang
	 * 上午 9:32:44
	 * @version 1.0.0
	 */
	public void setZSetValue(String key, Map<String, Double> map, Long seconds) {
		ZSetOperations<String, String> zsop = redisTemplate.opsForZSet();
		Set<String> keys = map.keySet();
		for (String obj : keys) {
			zsop.add(key, obj, map.get(obj));
		}
		setExpire(key, seconds);
	}

	/**
	 * @Description redis SortSet 删除成员
	 * @param key
	 * @param value 要删除的值
	 * @author liyanqiang
	 * 上午 9:32:44
	 * @version 1.0.0
	 */
	public void removeZSetValue(String key, String value) {
		ZSetOperations<String, String> zsop = redisTemplate.opsForZSet();
		zsop.remove(key, value);
	}

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
	public void setHash(String key, Map<String, String> map, Long seconds) {
		HashOperations<String, String, String> hop = redisTemplate.opsForHash();
		hop.putAll(key, map);
		redisTemplate.expire(key, seconds/1000, TimeUnit.MILLISECONDS);
	}
	 
	/**
	* @Description
	* @param key
	* @param value 序列化后的对象
	* @param seconds 过期时间一秒为单位
	* @author shiyang
	* 上午9:33:15
	* @version 1.0.0
	*/
	@Override
	public void setValue(String key, Object value, Long seconds) {
		ValueOperations<String, Object> opsForValue = redisTemplate.opsForValue();
		opsForValue.set(key, value,seconds,TimeUnit.SECONDS);
		
	}
	public RedisTemplate<String, Serializable> getRedisTemplate() {
		return redisTemplate;
	}

	public void setRedisTemplate(
			RedisTemplate<String, Serializable> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	/**
	* @Description 通过key 获取value
	* @param key
	* @return 根据存放对象接收
	* @author shiyang
	* 上午9:33:50
	* @version 1.0.0
	*/
	@Override
	public <M> M getValue(String key) {
		if(StringUtils.isBlank(key)){
			return null;
		}
		ValueOperations<String, M> opsForValue = redisTemplate.opsForValue();
		return opsForValue.get(key);
	}


	/**
	 * @Description 通过key 进行模糊查询
	 * @param key  abc*
	 * @return 根据存放对象接收
	 * @author shiyang
	 * 上午9:33:50
	 * @version 1.0.0
	 */
	@Override
	public Set<String> getValues(String key) {
		return redisTemplate.keys(key);
	}


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
	public List<String> getList(String key, long start, long end) {
		ListOperations<String, String> opsForValue = redisTemplate.opsForList();
		return opsForValue.range(key, start, end);
	}

	/**
	 * @Description 通过key 获取value set
	 * @param key
	 * @return 根据存放对象接收
	 * @author liyanqiang
	 * 上午9:33:50
	 * @version 1.0.0
	 */
	public Set<String> getSetValues(String key) {
		SetOperations<String, String> opsForValue = redisTemplate.opsForSet();
		return opsForValue.members(key);
	}

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
	public Set<String> getZSet(String key, long start, long end) {
		ZSetOperations<String, String> opsForValue = redisTemplate.opsForZSet();
		return opsForValue.range(key, start, end);
	}

	/**
	 * @Description 通过key 获取对象
	 * @param key
	 * @return 根据存放对象接收
	 * @author liyanqiang
	 * 上午9:33:50
	 * @version 1.0.0
	 */
	public Map<String, String> getHash(String key) {
		HashOperations<String, String, String> opsForValue = redisTemplate.opsForHash();
		return opsForValue.entries(key);
	}


	/**
	* @Description 删除key
	* @param key
	* @author shiyang
	* 上午9:34:30
	* @version 1.0.0
	*/
	@Override
	public void delKey(String key) {
		ValueOperations  opsForValue = redisTemplate.opsForValue();
		redisTemplate.delete(key);
	}

	private void setExpire(String key, Long seconds) {
		if (seconds != null) {
			redisTemplate.expire(key, seconds, TimeUnit.MILLISECONDS);
		}
	}

	/**
	 * @description 获取key失效时间
	 * @param key
	 * @return
	 * @author shiyang
	 * 2016年12月28日 下午8:26:36
	 */
	@Override
	public Long getExpire(String key) {
		return redisTemplate.getExpire(key);
	}
	
	
	/**
     * @param key
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
	public boolean exists(final String key) {
        return (boolean) redisTemplate.execute(new RedisCallback() {
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.exists(key.getBytes());
            }
        });
    }

}
//	        