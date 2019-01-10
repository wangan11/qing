package com.wangan.qing.upms.dao.mapper;

import com.wangan.qing.upms.dao.model.UpmsUser;
import com.wangan.qing.upms.dao.model.UpmsUserExample;

import java.util.List;

/**
 * @author wangan on 2019/1/10
 * @description
 */
public interface UpmsUserMapper {

	List<UpmsUser> selectByExample(UpmsUserExample example);

}
