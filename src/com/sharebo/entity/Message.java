package com.sharebo.entity;
import java.io.Serializable;
import java.util.Date;

//消息记录表
public class Message  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String msgid;              
	private int msgType;//'消息类型（0：判断消息，1，文字消息）'
	private String msgContent;
	private UserInfo user;//'用户id'
	private OrderInfo order;//'订单id(查看详情的订单号) 可以为空（为空也可以代表是文字消息）'
	private Date messageTime;//消息创建时间
	public String getMsgid() {
		return msgid;
	}
	public void setMsgid(String msgid) {
		this.msgid = msgid;
	}
	public int getMsgType() {
		return msgType;
	}
	public void setMsgType(int msgType) {
		this.msgType = msgType;
	}
	public String getMsgContent() {
		return msgContent;
	}
	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}
	public UserInfo getUser() {
		return user;
	}
	public void setUser(UserInfo user) {
		this.user = user;
	}
	public OrderInfo getOrder() {
		return order;
	}
	public void setOrder(OrderInfo order) {
		this.order = order;
	}
	public Date getMessageTime() {
		return messageTime;
	}
	public void setMessageTime(Date messageTime) {
		this.messageTime = messageTime;
	}
	public Message(String msgid, int msgType, String msgContent, UserInfo user,
			OrderInfo order, Date messageTime) {
		this.msgid = msgid;
		this.msgType = msgType;
		this.msgContent = msgContent;
		this.user = user;
		this.order = order;
		this.messageTime = messageTime;
	}
	public Message() {
	}
}
