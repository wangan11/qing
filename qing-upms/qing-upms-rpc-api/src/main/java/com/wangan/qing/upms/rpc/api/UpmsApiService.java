package com.wangan.qing.upms.rpc.api;

import com.wangan.qing.upms.dao.model.UpmsUser;

/**
 * @author wangan on 2019/1/10
 * @description
 */
public interface UpmsApiService {

	UpmsUser findUserByUsername(String username);
}
