package com.xmg.p2p.base.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmg.p2p.base.domain.Iplog;
import com.xmg.p2p.base.mapper.IplogMapper;
import com.xmg.p2p.base.query.IplogQuery;
import com.xmg.p2p.base.query.PageResult;
import com.xmg.p2p.base.service.IplogService;

@Service
public class IplogServiceImpl implements IplogService {

	@Autowired
	private IplogMapper iplogMapper;
	
	@Override
	public PageResult query(IplogQuery qo) {
		int count=this.iplogMapper.getCount(qo);
		if(count>0) {
			List<Iplog> iplogs=this.iplogMapper.queryList(qo);
			return new PageResult(qo.getPageSize(), iplogs, count, qo.getCurrentPage());
		}
		return PageResult.empty(qo.getPageSize());
	}

}
