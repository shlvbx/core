package com.xmg.p2p.base.service;

import com.xmg.p2p.base.query.IplogQuery;
import com.xmg.p2p.base.query.PageResult;

public interface IplogService {

	PageResult query(IplogQuery iplogQuery);
}
