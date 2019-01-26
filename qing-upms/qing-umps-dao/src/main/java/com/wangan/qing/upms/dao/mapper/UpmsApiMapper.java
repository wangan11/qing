package com.wangan.qing.upms.dao.mapper;

import com.wangan.qing.upms.dao.model.UpmsPermission;
import com.wangan.qing.upms.dao.model.UpmsRole;

import java.util.List;

/**
 * @author wangan on 2019/1/26
 * @description
 */
public interface UpmsApiMapper {

	// 根据用户id获取所拥有的权限
	List<UpmsPermission> selectUpmsPermissionByUpmsUserId(Integer upmsUserId);

	List<UpmsRole> selectUpmsRoleByUpmsUserId(Integer upmsUserId);

}
