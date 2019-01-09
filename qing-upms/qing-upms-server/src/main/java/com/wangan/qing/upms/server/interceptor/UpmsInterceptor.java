package com.wangan.qing.upms.server.interceptor;

import com.wangan.qing.common.util.PropertiesFileUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author wangan on 2019/1/9
 * @description
 */
public class UpmsInterceptor extends HandlerInterceptorAdapter {

	private static final Logger log= LoggerFactory.getLogger(UpmsInterceptor.class);
	public static final String QING_OSS_ALIYUN_OSS_POLICY=PropertiesFileUtil.getInstance("qing-oss-client").get("qing.oss.aliyun.oss.policy");;


	public UpmsInterceptor() {
		super();
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		request.setAttribute("QING_OSS_ALIYUN_OSS_POLICY",QING_OSS_ALIYUN_OSS_POLICY);
		if (request.getHeader("x-requested-with") != null && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")){
			//如果是ajax请求响应头会有，x-requested-with
			System.out.print("发生ajax请求...");
			return true;
			//response.setHeader("sessionstatus", "timeout");//在响应头设置session状态
		}
		Subject subject = SecurityUtils.getSubject();
		String username = subject.getPrincipal().toString();

		return super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		super.afterCompletion(request, response, handler, ex);
	}

	@Override
	public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		super.afterConcurrentHandlingStarted(request, response, handler);
	}
}
