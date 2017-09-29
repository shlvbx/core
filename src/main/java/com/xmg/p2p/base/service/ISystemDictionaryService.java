package com.xmg.p2p.base.service;



import java.util.List;

import com.xmg.p2p.base.domain.SystemDictionary;
import com.xmg.p2p.base.domain.SystemDictionaryItem;
import com.xmg.p2p.base.query.PageResult;
import com.xmg.p2p.base.query.SystemDictionaryQueryObject;

public interface ISystemDictionaryService {
	PageResult queryDics(SystemDictionaryQueryObject qo);
	PageResult queryItems(SystemDictionaryQueryObject qo);
	void saveOrUpdate(SystemDictionary sd);
	void saveOrUpdateItems(SystemDictionaryItem sdi);
	
	List<SystemDictionary> listDis();
	List<SystemDictionaryItem> selectBySn(String string);
	
}
