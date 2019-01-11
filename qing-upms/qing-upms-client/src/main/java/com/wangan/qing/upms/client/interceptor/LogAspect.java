package com.wangan.qing.upms.client.interceptor;

import com.wangan.qing.common.util.RequestUtil;
import com.wangan.qing.upms.dao.model.UpmsLog;
import com.wangan.qing.upms.rpc.api.UpmsApiService;
import com.wangan.qing.upms.rpc.api.UpmsLogService;
import org.apache.commons.lang.ObjectUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wangan on 2019/1/11
 * @description
 */
@Aspect
public class LogAspect {

	private static final Logger log = LoggerFactory.getLogger(LogAspect.class);
	private static Long startTime = 0L;
	private static Long endTime = 0L;

	@Autowired
	private UpmsLogService upmsLogService;

	@Before("execution(* *..controller..*.*(..))")
	public void beforeLogAspect() {
		log.info("beforeLogAspect");
		startTime = System.currentTimeMillis();
	}

	@After("execution(* *..controller..*.*(..))")
	public void afterLogAspect() {
		log.info("afterLogAspect");
	}

	@Around("execution(* *..controller..*.*(..))")
	public Object proceedLogAspect(ProceedingJoinPoint pjp) throws Throwable {
		RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
		HttpServletRequest request = servletRequestAttributes.getRequest();
		Object result = pjp.proceed();
		endTime=System.currentTimeMillis();
		log.info("doAround>>耗时：{}",endTime-startTime);
		UpmsLog upmsLog=new UpmsLog();
		upmsLog.setIp(RequestUtil.getIpAddr(request));
		upmsLog.setUri(request.getRequestURI());
		upmsLog.setUrl(request.getRequestURL()==null?"":request.getRequestURL().toString());
		upmsLog.setUserAgent(request.getHeader("User-Agent"));
		upmsLog.setBasePath(RequestUtil.getBasePath(request));
		if("GET".equals(request.getMethod())){
			upmsLog.setParameter(request.getQueryString());
		}else {
			upmsLog.setParameter(ObjectUtils.toString(request.getParameterMap()));
		}
		upmsLogService.insertUpmsLogRecord(upmsLog);
		return result;
	}
}
