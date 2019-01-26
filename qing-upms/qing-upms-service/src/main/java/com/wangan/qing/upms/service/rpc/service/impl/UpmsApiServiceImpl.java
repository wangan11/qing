package com.wangan.qing.upms.service.rpc.service.impl;

import com.wangan.qing.upms.dao.mapper.UpmsApiMapper;
import com.wangan.qing.upms.dao.mapper.UpmsLogMapper;
import com.wangan.qing.upms.dao.mapper.UpmsUserMapper;
import com.wangan.qing.upms.dao.model.*;
import com.wangan.qing.upms.rpc.api.UpmsApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author wangan on 2019/1/10
 * @description
 */
@Service
@Transactional
public class UpmsApiServiceImpl implements UpmsApiService {
	private static final Logger LOGGER= LoggerFactory.getLogger(UpmsApiServiceImpl.class);

	@Autowired
	UpmsLogMapper upmsLogMapper;
	@Autowired
	UpmsUserMapper upmsUserMapper;
	@Autowired
	UpmsApiMapper upmsApiMapper;


	@Override
	public UpmsUser findUserByUsername(String username) {
		UpmsUserExample upmsUserExample = new UpmsUserExample();
		upmsUserExample.createCriteria().andUsernameNotEqualTo(username);
		return null;
	}

	@Override
	public List<UpmsRole> selectUpmsRoleByUpmsUserId(Integer upmsUserId) {
		UpmsUser upmsUser = upmsUserMapper.selectByPrimaryKey(upmsUserId);
		if(null == upmsUser || 1==upmsUser.getLocked()){
			LOGGER.info("selectUpmsPermissionByUpmsUserId : UpmsUser={}",upmsUser);
			return  null;
		}
		List<UpmsRole> upmsRoles = upmsApiMapper.selectUpmsRoleByUpmsUserId(upmsUserId);
		return upmsRoles;
	}

	@Override
	public List<UpmsPermission> selectUpmsPermissionByUpmsUserId(Integer upmsUserId) {
		UpmsUser upmsUser = upmsUserMapper.selectByPrimaryKey(upmsUserId);
		if(null==upmsUser||1==upmsUser.getLocked()){
			LOGGER.info("selectUpmsPermissionByUpmsUserId : upmsUser={}",upmsUser);
		}
		List<UpmsPermission> upmsPermissions = upmsApiMapper.selectUpmsPermissionByUpmsUserId(upmsUserId);
		return upmsPermissions;
	}


}
