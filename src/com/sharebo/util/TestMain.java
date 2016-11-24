package com.sharebo.util;

import java.sql.SQLException;

import com.sharebo.entity.TaskInfo;
import com.sharebo.quartz.CancelTheOrder;
import com.sharebo.quartz.TaskTimer;

public class TestMain {
 
	/**
	 * @param args
	 * @throws SQLException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args)  {
		TaskInfo t=new TaskInfo();
		t.setJobName("测试用户。、。。");
		t.setOrderNo("123456789");
		TaskTimer.addJob(t, CancelTheOrder.class, 10);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		TaskInfo t1=new TaskInfo();
		t1.setJobName("测试用户。、。。aaa");
		t1.setOrderNo("12345");
		TaskTimer.addJob(t1, CancelTheOrder.class, 10);
	}
}

