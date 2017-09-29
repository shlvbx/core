package com.xmg.p2p.base.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmg.p2p.base.domain.Account;
import com.xmg.p2p.base.domain.Iplog;
import com.xmg.p2p.base.domain.Logininfo;
import com.xmg.p2p.base.domain.Userinfo;
import com.xmg.p2p.base.mapper.IplogMapper;
import com.xmg.p2p.base.mapper.logininfoMapper;
import com.xmg.p2p.base.service.IAcountService;
import com.xmg.p2p.base.service.ILogininfoService;
import com.xmg.p2p.base.service.IUserinfosService;
import com.xmg.p2p.base.util.BigConst;
import com.xmg.p2p.base.util.MD5;
import com.xmg.p2p.base.util.UserContext;
@Service
public class LogininfoServiceImpl implements ILogininfoService {

	@Autowired
	private logininfoMapper logininfoMapper;
	
	@Autowired
	private IUserinfosService userinfoService;
	
	@Autowired
	private IAcountService accountService;
	
	@Autowired
	private IplogMapper iplogMapper;
	
	@Override
	public void register(String username, String password) {
		int count=logininfoMapper.countUserByName(username);
		if(count<=0) {
			Logininfo logininfo=new Logininfo();
			logininfo.setUsername(username);
			logininfo.setPassword(MD5.encode(password));
			logininfo.setState(Logininfo.STATE_NORBAL);
			logininfo.setUserType(Logininfo.USER_CLIENT);
			System.out.println(logininfo);
			logininfoMapper.insert(logininfo);
			System.out.println(logininfo);
			
			//初始化用户账户信息
			Account account=new Account();
			account.setId(logininfo.getId());
			this.accountService.add(account);
			
			Userinfo userinfo=new Userinfo();
			userinfo.setId(logininfo.getId());
			this.userinfoService.add(userinfo);
		}else{
			throw new RuntimeException("用户已经存在");
		}
	}

	@Override
	public Boolean checkUserName(String username) {
		return this.logininfoMapper.countUserByName(username)>0;
	}
	
	@Override
	public Logininfo login(String username, String password,String ip,int userType) {
		
		Logininfo info=this.logininfoMapper.login( username, MD5.encode(password),userType);
		
		Iplog iplog=new Iplog();
		iplog.setIp(ip);
		iplog.setUserName(username);
		iplog.setLoginTime(new Date());
		if(info!=null) {
			iplog.setState(Iplog.STATE_SUCESS);
			UserContext.putCurrent(info);
		}else {
			iplog.setState(Iplog.STATE_FALLIED);
		}
		
		this.iplogMapper.insert(iplog);
		
		return info;
	}

	//初始化第一个管理员
	@Override
	public void initAdmin() {
		int count=this.logininfoMapper.countUserByUserType(Logininfo.USER_MANAGER);
		if(count==0) {
			Logininfo info=new Logininfo();
			info.setUsername(BigConst.DEFAULT_USERNAME);
			info.setPassword(MD5.encode(BigConst.DEFAULT_PASSWORD));
			info.setUserType(Logininfo.USER_MANAGER);
			this.logininfoMapper.insert(info);
		}
	}

	@Override
	public Logininfo selectById(Long id) {
		return this.logininfoMapper.selectByPrimaryKey(id);
	}

}
