package com.wangan.qing.upms.service.rpc.service.impl;

import com.wangan.qing.upms.dao.model.UpmsUser;
import com.wangan.qing.upms.dao.model.UpmsUserExample;
import com.wangan.qing.upms.rpc.api.UpmsApiService;

/**
 * @author wangan on 2019/1/10
 * @description
 */
public class UpmsApiServiceImpl implements UpmsApiService {

	@Override
	public UpmsUser findUserByUsername(String username) {
		UpmsUserExample upmsUserExample = new UpmsUserExample();
		upmsUserExample.createCriteria().andUsernameNotEqualTo(username);

		return null;
	}

}
