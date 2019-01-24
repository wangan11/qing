package com.wangan.qing.upms.client.shiro.session;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SessionContext;
import org.apache.shiro.session.mgt.SessionFactory;
import org.apache.shiro.web.session.mgt.WebSessionContext;

import javax.servlet.http.HttpServletRequest;

public class UpmsSessionFactory implements SessionFactory {

    @Override
    public Session createSession(SessionContext sessionContext) {
        UpmsSession session=new UpmsSession();
        if(null!=sessionContext&&sessionContext instanceof WebSessionContext){
            WebSessionContext webSessionContext = (WebSessionContext) sessionContext;
            HttpServletRequest servletRequest = (HttpServletRequest) webSessionContext.getServletRequest();
            session.setHost(servletRequest.getRemoteHost());
            session.setUserAgent(servletRequest.getHeader("User-Agent"));
        }

        return session;
    }
}
