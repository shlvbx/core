package com.xmg.p2p.base.mapper;

import com.xmg.p2p.base.domain.Iplog;
import com.xmg.p2p.base.query.IplogQuery;

import java.util.List;

public interface IplogMapper {
    

    int insert(Iplog record);

    Iplog selectByPrimaryKey(Long id);

    int updateByPrimaryKey(Iplog record);

	int getCount(IplogQuery iplogQuery);

	List<Iplog> queryList(IplogQuery iplogQuery);
}