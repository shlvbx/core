package com.xmg.p2p.base.mapper;

import java.util.List;

import com.xmg.p2p.base.domain.SystemDictionaryItem;
import com.xmg.p2p.base.query.PageResult;
import com.xmg.p2p.base.query.SystemDictionaryQueryObject;

public interface SystemDictionaryItemMapper {

    int insert(SystemDictionaryItem record);

    SystemDictionaryItem selectByPrimaryKey(Long id);

    List<SystemDictionaryItem> selectAll();

    int updateByPrimaryKey(SystemDictionaryItem record);
    
    /**
     * 分页
     */
    int queryForCount(SystemDictionaryQueryObject qo);
    
    List<SystemDictionaryItem> queryItems(SystemDictionaryQueryObject qo);

	List<SystemDictionaryItem> selectAllBySn(String string); 
}