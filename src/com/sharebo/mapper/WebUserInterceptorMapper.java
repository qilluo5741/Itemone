package com.sharebo.mapper;

import java.util.Map;

public interface WebUserInterceptorMapper {
	 int valMenuPermissions(Map<String,String> map);
	 int valHrefIsExist(String href);
}
