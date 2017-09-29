package com.xmg.p2p.base.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmg.p2p.base.domain.MailVerify;
import com.xmg.p2p.base.domain.Userinfo;
import com.xmg.p2p.base.mapper.MailVerifyMapper;
import com.xmg.p2p.base.mapper.UserinfoMapper;
import com.xmg.p2p.base.service.ISendVerifyCode;
import com.xmg.p2p.base.service.IUserinfosService;
import com.xmg.p2p.base.util.BigConst;
import com.xmg.p2p.base.util.BitStatesUtils;
import com.xmg.p2p.base.util.DateUtil;
import com.xmg.p2p.base.util.UserContext;

@Service
public class UserinfoServiceImpl implements IUserinfosService {

	@Autowired
	private UserinfoMapper userinfoMapper;
	
	@Autowired
	private ISendVerifyCode sendVerifyCode;
	
	@Autowired
	private MailVerifyMapper mailVerifyMapper;
	
	@Override
	public void update(Userinfo userinfo) {
		int result=this.userinfoMapper.updateByPrimaryKey(userinfo);
		if(result==0) {
			throw new RuntimeException("乐观锁失效：userinfo"+userinfo.getId());
		}
	}

	@Override
	public void add(Userinfo userinfo) {
		// TODO Auto-generated method stub
		this.userinfoMapper.insert(userinfo);
	}

	@Override
	public Userinfo get(Long id) {
		return this.userinfoMapper.selectByPrimaryKey(id);
	}

	@Override
	public void isBindPhone(String phoneNumber, String verifyCode) {
		
		//和session中进行校验
		if(this.sendVerifyCode.validate(phoneNumber, verifyCode)) {
			Userinfo userinfo = this.get(UserContext.getCurrent().getId());
			if(!userinfo.getIsBindPhone()) {
				
				userinfo.setPhoneNumber(phoneNumber);
				userinfo.addState(BitStatesUtils.OP_BIND_PHONE);
				
				this.update(userinfo);
			}else {
				throw new RuntimeException("绑定失败");
			}
		}
	}

	/**
	 * 邮箱绑定
	 */

	@Override
	public void isBIndEmail(String uuid) {
		long id=Long.valueOf(uuid);
		MailVerify mv = this.mailVerifyMapper.selectByUuid(id);
		
		if(mv!=null&&//是否为null
				(DateUtil.getBetweenSecond(//是否在有效期内
						new Date(), mv.getSendEmailTime()))<=BigConst.VERIFYCODEtTIME*24*3600) {
			Userinfo userinfo = this.userinfoMapper.selectByPrimaryKey(id);
			
			if(!userinfo.getIsBindEmail()) {
				
				userinfo.setEmail(mv.getEmail());
				userinfo.setBitState(BitStatesUtils.addState(userinfo.getBitState(),
						BitStatesUtils.OP_BIND_EMAIL));
				this.update(userinfo);
			}else {
				throw new RuntimeException("已经有邮箱被绑定了");
			}
				
		}else {
				throw new RuntimeException("绑定时间已经过期");
			}
		}

	
	@Override
	public Userinfo selectById(Long id) {
		return this.userinfoMapper.selectByPrimaryKey(id);
	}
	
	//保存个人的基本资料
	@Override
	public void saveOrUpdate(Userinfo userinfo) {
		
		try {
			Long id = UserContext.getCurrent().getId();
			Userinfo oldUserinfo = this.selectById(id);
			
			//封装userinfo
			oldUserinfo.setEducationBackground(userinfo.getEducationBackground());
			oldUserinfo.setIncomeGrade(userinfo.getIncomeGrade());
			oldUserinfo.setKidCount(userinfo.getKidCount());
			oldUserinfo.setMarriage(userinfo.getMarriage());
			oldUserinfo.setHouseCondition(userinfo.getHouseCondition());
			if(!oldUserinfo.getIsBaseInfo()) {
				oldUserinfo.addState(BitStatesUtils.OP_BASIC_INFO);
			}
			this.update(oldUserinfo);
		} catch (Exception e) {
			throw new RuntimeException("保存或者更新失败");
		}
		
	}
	
}
