package com.sharebo.quartz;

import java.util.HashMap;
import java.util.Map;

import com.sharebo.entity.TaskInfo;

/**
 * 存入工作任务
 * 
 * @author niewei
 * 
 */
public class BaseTask {
	// 工作任务，自动退款，自动拒绝
	protected static Map<String, TaskInfo> taskrefund = new HashMap<String, TaskInfo>();
	// 工作任务，自动取消
	protected static Map<String, TaskInfo> taskcancel = new HashMap<String, TaskInfo>();
}
