package com.wangan.qing.common.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSource extends AbstractRoutingDataSource {

    private Logger log=LoggerFactory.getLogger(this.getClass());

    private static ThreadLocal<String> routeKey = new ThreadLocal<String>();

    protected String determineCurrentLookupKey() {
        String dataSource = getDataSource();
        log.info("当前操作使用的数据源：{}", dataSource);
        return dataSource;
    }


    public static String getDataSource() {
        String dataSource = routeKey.get();
        if (dataSource == null) {

        }
        return dataSource;
    }

    public static void setDataSource(String key){
        routeKey.set(key);
    }

    public static void removeDataSource(String key){
        routeKey.remove();
    }

}
