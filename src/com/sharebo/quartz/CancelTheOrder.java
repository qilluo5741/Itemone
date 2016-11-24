package com.sharebo.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.sharebo.config.QuartzConfiig;
import com.sharebo.entity.TaskInfo;
import com.sharebo.util.httpClient.HttpUtil;
/**
 * 自动取消订单
 * @author niewei
 *
 */
public class CancelTheOrder extends BaseTask implements Job{
	//执行取消订单的操作
	public void execute(JobExecutionContext context) throws JobExecutionException {
		TaskInfo  task_val=(TaskInfo)context.getMergedJobDataMap().get("task");
    	if(taskcancel.get(task_val.getJobName())!=null){
    		/////////////////////////////执行任务///////////////////////////////
    		//通过jobName（订单编号） 自动取消订单
    		System.out.println("执行任务："+task_val.getJobName());
    		String aa=HttpUtil.request_post(QuartzConfiig.CANCEL_URL,"jobName="+task_val.getJobName()+"&orderNo="+task_val.getOrderNo());
    		System.out.println("调用任务结果：==================="+aa);
    		///////////////////////////////////////////////////////////////////
    		taskcancel.remove(task_val.getJobName());//执行成功清理集合对象
    		TaskTimer.closeJob(task_val.getJobName());//关闭计时
    	}
    	else{
    		//添加验证——任务
    		taskcancel.put(task_val.getJobName(),task_val);
    	}
	}
}
