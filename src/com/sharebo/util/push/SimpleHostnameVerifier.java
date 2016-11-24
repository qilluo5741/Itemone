package com.sharebo.util.push;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

public class SimpleHostnameVerifier implements HostnameVerifier {

	public boolean verify(String hostname, SSLSession session) {
		return true;
	}

}
