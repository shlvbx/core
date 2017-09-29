package com.xmg.p2p.base.service;

import com.xmg.p2p.base.domain.Logininfo;

public interface ILogininfoService {
	void register(String username,String password);

	Boolean checkUserName(String username);

	//用户的登录校验
	Logininfo login(String username, String password, String ip, int userType);

	void initAdmin();

	Logininfo selectById(Long id);
}
