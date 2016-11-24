package com.sharebo.entity;

import java.io.Serializable;
import java.util.Date;
/**
 * 车位收藏表
 * @author Administrator
 *
 */
public class Collect  implements Serializable {
		private static final long serialVersionUID = 1L;
		private String collectid;//'车位收藏表Id',
		private UserInfo user;//'用户表（外键）',
		private Parkingspace park;//'车位表（外键）',
		private Date collecttime;//'标记时间',
		public String getCollectid() {
			return collectid;
		}
		public void setCollectid(String collectid) {
			this.collectid = collectid;
		}
		public UserInfo getUser() {
			return user;
		}
		public void setUser(UserInfo user) {
			this.user = user;
		}
		public Parkingspace getPark() {
			return park;
		}
		public void setPark(Parkingspace park) {
			this.park = park;
		}
		public Date getCollecttime() {
			return collecttime;
		}
		public void setCollecttime(Date collecttime) {
			this.collecttime = collecttime;
		}
}
