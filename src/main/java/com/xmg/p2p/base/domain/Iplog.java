package com.xmg.p2p.base.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
@Setter@Getter
public class Iplog extends BaseDomain{
	
	public static final int STATE_SUCESS=1;
	public static final int STATE_FALLIED=0;
	
	private String ip;
	private String userName;
	private Date loginTime;
	private int state;
	private int  userType;
	
	public String getDispayState() {
		return this.state==STATE_SUCESS ? "登录成功":"登录失败";
	}
	
	public String getDisplayUserType() {
		return this.state==Logininfo.USER_CLIENT ? "前端用户":"后台管理员";
	}
}
