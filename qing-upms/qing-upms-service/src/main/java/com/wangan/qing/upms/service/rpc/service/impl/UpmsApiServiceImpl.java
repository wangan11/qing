package com.wangan.qing.upms.service.rpc.service.impl;

import com.wangan.qing.upms.dao.mapper.UpmsLogMapper;
import com.wangan.qing.upms.dao.model.UpmsLog;
import com.wangan.qing.upms.dao.model.UpmsUser;
import com.wangan.qing.upms.dao.model.UpmsUserExample;
import com.wangan.qing.upms.rpc.api.UpmsApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author wangan on 2019/1/10
 * @description
 */
@Service
@Transactional
public class UpmsApiServiceImpl implements UpmsApiService {

	@Autowired
	UpmsLogMapper upmsLogMapper;

	@Override
	public UpmsUser findUserByUsername(String username) {
		UpmsUserExample upmsUserExample = new UpmsUserExample();
		upmsUserExample.createCriteria().andUsernameNotEqualTo(username);
		return null;
	}


}
