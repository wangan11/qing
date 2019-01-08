package com.wangan.qing.common.plugin;

import com.wangan.qing.common.util.AESUtil;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

public class EncryptPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {

    private String[] propertyNames={"master.jdbc.password","slave.jdbc.password","master.redis.password"};

    @Override
    protected String convertProperty(String propertyName, String propertyValue) {
        for (String p:propertyNames){
            if(p.equalsIgnoreCase(propertyName)){
                String value = AESUtil.aesDecode(propertyValue);
                return  value;
            }
        }
        return super.convertProperty(propertyName, propertyValue);
    }
}
