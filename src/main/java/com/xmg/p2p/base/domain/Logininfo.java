package com.xmg.p2p.base.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Setter
@Getter
@ToString
public class Logininfo extends BaseDomain {
	
	//账户是否被锁定
	public static final int STATE_NORBAL=0;//正常
	public static final int STATE_LOCK=1;//锁定
	
	public static final int USER_MANAGER=0;
	public static final int USER_CLIENT=1;
	
	
    private String username;

    private String password;

    private int state;
    
    private int userType;//区分用户类型
  
}