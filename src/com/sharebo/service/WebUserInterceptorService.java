package com.sharebo.service;

import java.util.Map;

public interface WebUserInterceptorService {
	boolean valMenuPermissions(Map<String,String> map);
	int valHrefIsExist(String href);
}
