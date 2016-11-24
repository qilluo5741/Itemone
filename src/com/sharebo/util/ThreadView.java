package com.sharebo.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * 连接池视器
 * @author niewei
 *
 */
public class ThreadView {
	public static ComboPooledDataSource threadView(){
		ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
		ComboPooledDataSource ds=(ComboPooledDataSource) ac.getBean("dataSource");
		/* System.out.println(ds.getMaxPoolSize());// 最大连接数
         System.out.println(ds.getMinPoolSize());// 最小连接数
         System.out.println(ds.getNumBusyConnections());// 正在使用连接数
         System.out.println(ds.getNumIdleConnections());// 空闲连接数
         System.out.println(ds.getNumConnections());// 总连接数
		 */		
		return ds;
	}
}
