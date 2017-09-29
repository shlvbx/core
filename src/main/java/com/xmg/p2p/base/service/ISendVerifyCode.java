package com.xmg.p2p.base.service;

public interface ISendVerifyCode {
	void sendVerifyCode(String phoneNumber);
	boolean validate(String phoneNumber,String code);
}
