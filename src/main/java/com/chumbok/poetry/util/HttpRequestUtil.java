package com.chumbok.poetry.util;

import javax.servlet.http.HttpServletRequest;

public class HttpRequestUtil {

	public static String getBaseURL(HttpServletRequest request) {
		return String.format("%s://%s:%d%s/", request.getScheme(),
				request.getServerName(), request.getServerPort(),
				getContextPath(request));
	}

	public static String getContextPath(HttpServletRequest request) {
		return request.getContextPath();
	}

}
