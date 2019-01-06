package com.wangan.qing.common.base;


import java.util.List;

public  abstract class BaseServiceImpl<Mapper,Record,Example> implements BaseService<Record,Example>  {


    public List<Record> selectByExampleForStartPage(Example example, Integer pageNum, Integer pageSize) {
        return null;
    }
}
