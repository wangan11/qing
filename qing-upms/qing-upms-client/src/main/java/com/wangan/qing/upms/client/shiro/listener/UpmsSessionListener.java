package com.wangan.qing.upms.client.shiro.listener;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UpmsSessionListener implements SessionListener {
    private static final Logger log=LoggerFactory.getLogger(UpmsSessionListener.class);
    @Override
    public void onStart(Session session) {
        log.info("seesion创建:"+session.getId());
    }

    @Override
    public void onStop(Session session) {
        log.info("session会话停止:"+session.getId());
    }

    @Override
    public void onExpiration(Session session) {
        log.info("session会话过期:"+session.getId());
    }
}
