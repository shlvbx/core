package com.xmg.p2p.base.service;

import com.xmg.p2p.base.domain.Userinfo;

/**
 * 用户的相关信息服务
 * @author Administrator
 *
 */
public interface IUserinfosService {
	void  update(Userinfo userinfo);

	void add(Userinfo userinfo);

	Userinfo get(Long id);

	void isBindPhone(String phoneNumber, String verifyCode);
	void isBIndEmail(String uuid);

	Userinfo selectById(Long id);

	void saveOrUpdate(Userinfo userinfo);
}
