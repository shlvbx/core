package com.xmg.p2p.base.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmg.p2p.base.domain.Account;
import com.xmg.p2p.base.mapper.AccountMapper;
import com.xmg.p2p.base.service.IAcountService;

@Service
public class AcountServiceImpl implements IAcountService {
	
	@Autowired
	private AccountMapper accountMapper;
	
	@Override
	public void update(Account account) {
		int result=this.accountMapper.updateByPrimaryKey(account);
		System.out.println("hahah");
		if(result==0) {
			throw new RuntimeException("乐观锁失效,Account:"+account.getId());
		}
	}

	@Override
	public void add(Account account) {
		// TODO Auto-generated method stub
		this.accountMapper.insert(account);
	}

	@Override
	public Account get(Long id) {
		return this.accountMapper.selectByPrimaryKey(id);
	}

}
