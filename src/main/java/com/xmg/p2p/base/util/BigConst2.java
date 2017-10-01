package com.xmg.p2p.base.util;
/**
 * 定义系统常量
 * @author Administrator
 *
 */

import java.math.BigDecimal;
import java.util.Date;
public class BigConst2{
	//定义存储精度
	public static final int STORE_SCALE=4;

	//定义运算精度
	public static final int CAL_SCALE=8;
	
	//定义显示精度
	public static final int DISPLAY_SCALE=2;
	//定义显示的初始值
	public static final BigDecimal ZERO=new BigDecimal("0.0000");
	
	//定义默认的admin的初始化参数
	public static final String DEFAULT_USERNAME="admin";
	public static final String DEFAULT_PASSWORD="1111";

	public static final long VERIFYCODEtTIME = 5L;

	public static final long VERIFYCODE_VALID_TIME = 120L;
	
	public static final int BORROW_SCORE=30;//风控分数
	
}
