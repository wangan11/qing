package com.wangan.qing.upms.rpc.api;

import com.wangan.qing.upms.dao.model.UpmsPermission;
import com.wangan.qing.upms.dao.model.UpmsRole;
import com.wangan.qing.upms.dao.model.UpmsUser;

import java.util.List;

/**
 * @author wangan on 2019/1/10
 * @description
 */
public interface UpmsApiService {

	UpmsUser findUserByUsername(String username);

	List<UpmsRole> selectUpmsRoleByUpmsUserId(Integer upmsUserId);

	 List<UpmsPermission> selectUpmsPermissionByUpmsUserId(Integer upmsUserId);
}
