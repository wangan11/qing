package com.wangan.qing.upms.server.controller;

import com.wangan.qing.common.base.BaseController;
import com.wangan.qing.common.util.RedisUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wangan on 2019/1/28
 * @description
 */
 @Controller
 @RequestMapping("/sso")
public class SSOController extends BaseController {

	private static final Logger LOGGER= LoggerFactory.getLogger(SSOController.class);
	// 全局会话key
	private final static String ZHENG_UPMS_SERVER_SESSION_ID = "zheng-upms-server-session-id";


	@RequestMapping(value = "/login",method = RequestMethod.GET)
	public String login(HttpServletRequest request){
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		String serverSessionId = session.getId().toString();
		// 判断是否已登录，如果已登录，则回跳
		String code = RedisUtil.get(ZHENG_UPMS_SERVER_SESSION_ID + "_" + serverSessionId);
		// code校验值
		if (StringUtils.isNotBlank(code)) {
			// 回跳
			String backurl = request.getParameter("backurl");
			String username = (String) subject.getPrincipal();
			if (StringUtils.isBlank(backurl)) {
				backurl = "/";
			} else {
				if (backurl.contains("?")) {
					backurl += "&upms_code=" + code + "&upms_username=" + username;
				} else {
					backurl += "?upms_code=" + code + "&upms_username=" + username;
				}
			}
			LOGGER.debug("认证中心帐号通过，带code回跳：{}", backurl);
			return "redirect:" + backurl;
		}
		return "/sso/login.jsp";
	}

}
