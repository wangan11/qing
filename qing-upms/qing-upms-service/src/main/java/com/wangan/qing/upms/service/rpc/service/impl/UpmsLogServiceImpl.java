package com.wangan.qing.upms.service.rpc.service.impl;

import com.wangan.qing.upms.dao.mapper.UpmsLogMapper;
import com.wangan.qing.upms.dao.model.UpmsLog;
import com.wangan.qing.upms.rpc.api.UpmsLogService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author wangan on 2019/1/11
 * @description
 */

public class UpmsLogServiceImpl implements UpmsLogService {

	@Autowired
	private UpmsLogMapper upmsLogMapper;

	@Override
	public int insertUpmsLogRecord(UpmsLog upmsLog) {
		return upmsLogMapper.insertUpmsLogRecord(upmsLog);
	}
}
