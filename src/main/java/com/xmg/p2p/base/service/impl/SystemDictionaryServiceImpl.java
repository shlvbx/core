package com.xmg.p2p.base.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmg.p2p.base.domain.SystemDictionary;
import com.xmg.p2p.base.domain.SystemDictionaryItem;
import com.xmg.p2p.base.mapper.SystemDictionaryItemMapper;
import com.xmg.p2p.base.mapper.SystemDictionaryMapper;
import com.xmg.p2p.base.query.PageResult;
import com.xmg.p2p.base.query.SystemDictionaryQueryObject;
import com.xmg.p2p.base.service.ISystemDictionaryService;

@Service
public class SystemDictionaryServiceImpl implements ISystemDictionaryService {

	@Autowired
	private SystemDictionaryMapper systemDictionaryMapper;
	
	@Autowired
	private SystemDictionaryItemMapper systemDictionaryItemMapper;
	@Override
	public PageResult queryDics(SystemDictionaryQueryObject qo) {
		int count =this.systemDictionaryMapper.queryForCount(qo);
		if(count!=0) {
			List<SystemDictionary> lists = this.systemDictionaryMapper.query(qo);
			
			return new PageResult(qo.getPageSize(), lists, count, qo.getCurrentPage());
		}
		return PageResult.empty(qo.getPageSize());
	}

	@Override
	public void saveOrUpdate(SystemDictionary sd) {
		if(sd.getId()!=null) {
			this.systemDictionaryMapper.updateByPrimaryKey(sd);
		}else {
			this.systemDictionaryMapper.insert(sd);
		}
	}

	@Override
	public PageResult queryItems(SystemDictionaryQueryObject qo) {
		int count=this.systemDictionaryItemMapper.queryForCount(qo);
		if(count!=0) {
			List<SystemDictionaryItem> items = this.systemDictionaryItemMapper.queryItems(qo);
			return new PageResult(qo.getPageSize(), items, count, qo.getCurrentPage());
		}
		return PageResult.empty(qo.getPageSize());
	}

	@Override
	public void saveOrUpdateItems(SystemDictionaryItem sdi) {
		if(sdi.getId()!=null) {
			this.systemDictionaryItemMapper.updateByPrimaryKey(sdi);
		}else {
			this.systemDictionaryItemMapper.insert(sdi);
		}
	}

	@Override
	public List<SystemDictionary> listDis() {
		return this.systemDictionaryMapper.selectAll();
	}

	@Override
	public List<SystemDictionaryItem> selectBySn(String string) {
		return this.systemDictionaryItemMapper.selectAllBySn(string);
	}

}
