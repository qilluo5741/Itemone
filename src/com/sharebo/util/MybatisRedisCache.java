package com.sharebo.util;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.ibatis.cache.Cache;

import com.sharebo.util.RedisUtil;
import com.sharebo.util.SerializeUtil;

public class MybatisRedisCache implements Cache {
    @SuppressWarnings("unused")
	private static Logger logger = LoggerFactory.getLogger(MybatisRedisCache.class); 
    /** The ReadWriteLock. */ 
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
     
    private String id;
     
    public MybatisRedisCache(final String id) {  
        if (id == null) {
            throw new IllegalArgumentException("Cache instances require an ID");
        }
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>MybatisRedisCache:id="+id);
        this.id = id;
    }  
     
    public String getId() {
        return this.id;
    }
 
    public void putObject(Object key, Object value) {
    	 System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>put:"+key+"="+value);
        RedisUtil.getJedis().set(SerializeUtil.serialize(key.toString()), SerializeUtil.serialize(value));
    }
 
    public Object getObject(Object key) {
        Object value = SerializeUtil.unserialize(RedisUtil.getJedis().get(SerializeUtil.serialize(key.toString())));
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>get:"+key+"="+value);
        return value;
    }
 
    public Object removeObject(Object key) {
        return RedisUtil.getJedis().expire(SerializeUtil.serialize(key.toString()),0);
    }
 
    public void clear() {
        RedisUtil.getJedis().flushDB();
    }
 
    public int getSize() {
        return Integer.valueOf(RedisUtil.getJedis().dbSize().toString());
    }
 
    public ReadWriteLock getReadWriteLock() {
        return readWriteLock;
    }
     
}