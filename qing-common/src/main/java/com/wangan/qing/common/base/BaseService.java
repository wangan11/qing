package com.wangan.qing.common.base;

import java.util.List;

/**
 * BaseService 接口
 * @param <Record>
 * @param <Example>
 */
public interface BaseService <Record,Example> {

    /**
     * 分页查询
     * @param example
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<Record> selectByExampleForStartPage(Example example,Integer pageNum,Integer pageSize);


}
