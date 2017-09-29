package com.xmg.p2p.base.domain;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
@Setter@Getter
public class VerifyCodeVo implements Serializable {
	private String phoneNumber;
	private String code;
	private Date sendTime;
	public VerifyCodeVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public VerifyCodeVo(String phoneNumber, String code, Date sendTime) {
		super();
		this.phoneNumber = phoneNumber;
		this.code = code;
		this.sendTime = sendTime;
	}
	
}
