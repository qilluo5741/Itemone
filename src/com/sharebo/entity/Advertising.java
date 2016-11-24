package com.sharebo.entity;

import java.io.Serializable;

/**
 * 广告表Advertising
 * @author Administrator
 *
 */
public class Advertising  implements Serializable{
		private static final long serialVersionUID = 1L;
		private String advid;      // '广告主键',
		private String adname;     //
		private String adv_img_url;//广告图片路径',
		private String adv_href;
		public String getAdvid() {
			return advid;
		}
		public void setAdvid(String advid) {
			this.advid = advid;
		}
		public String getAdname() {
			return adname;
		}
		public void setAdname(String adname) {
			this.adname = adname;
		}
		public String getAdv_img_url() {
			return adv_img_url;
		}
		public void setAdv_img_url(String advImgUrl) {
			adv_img_url = advImgUrl;
		}
		public String getAdv_href() {
			return adv_href;
		}
		public void setAdv_href(String advHref) {
			adv_href = advHref;
		}
}
