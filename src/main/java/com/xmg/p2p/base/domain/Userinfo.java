package com.xmg.p2p.base.domain;


import com.xmg.p2p.base.util.BitStatesUtils;

import lombok.Getter;
import lombok.Setter;

/**
 * 用户的相关信息
 * 
 * 
 */
@Setter
@Getter
public class Userinfo extends BaseDomain {
	
	private int version; // 版本号
	private long bitState = 0; // 用户状态码
	private String realName;
	private String idNumber;
	private String phoneNumber;
	private String email;
	
	private int score ; // 用户的风控总分数
//	private Long realAuthId ; //用户实名认证  判断是否有对应的实名认证对象  冗余数据

	private SystemDictionaryItem incomeGrade; // 收入
	private SystemDictionaryItem Marriage; // 婚姻情况
	private SystemDictionaryItem kidCount; // 子女情况
	private SystemDictionaryItem educationBackground; // 学历
	private SystemDictionaryItem houseCondition; // 住房条件
	
	//手机是否被绑定
	public boolean getIsBindPhone() {
		return BitStatesUtils.hasState(this.bitState, BitStatesUtils.OP_BIND_PHONE);
	}

	//邮箱是否被绑定
	public boolean getIsBindEmail() {
		return BitStatesUtils.hasState(this.bitState, BitStatesUtils.OP_BIND_EMAIL);
	}
	//添加状态
	public void addState(Long state) {
		this.bitState=BitStatesUtils.addState(this.bitState, state);
	}
	
	//用户基本信息是否填写
	public boolean getIsBaseInfo() {
		return BitStatesUtils.hasState(this.bitState, BitStatesUtils.OP_BASIC_INFO);
	}
	
	//视频是否认证
	public boolean getIsVideoAuth() {
		return BitStatesUtils.hasState(this.bitState, BitStatesUtils.OP_VEDIO_AUTH);
	}
	
	//是否实名认证
	public boolean getIsRealAuth() {
		return BitStatesUtils.hasState(this.bitState, BitStatesUtils.OP_REAL_AUTH);
	}
}
