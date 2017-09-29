package com.xmg.p2p.base.util;

import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.xmg.p2p.base.domain.Logininfo;
import com.xmg.p2p.base.domain.VerifyCodeVo;

/**
 * 用户的上下文
 * 
 * 
 */
// 通过监听请求的创建 将请求扔到threadlocal里 源代码RequestContextListener类
public class UserContext {

	public static final String LOGININFO_IN_SESSION = "logininfo";
	public static final String VERIFYCODE_IN_SESSION = "verifycode_in_session";

	
	 //注意web.xml配置该监听器 RequestContextHolder
	private static HttpSession getSession() {
		return ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest().getSession();
	}

	public static void putCurrent(Logininfo current) {
		getSession().setAttribute(LOGININFO_IN_SESSION, current);
	}

	
	public static Logininfo getCurrent() {
		return (Logininfo) getSession().getAttribute(LOGININFO_IN_SESSION);
	}

	//手机验证码存放
	public static VerifyCodeVo getVerifyCodeVo() {
		return (VerifyCodeVo) getSession().getAttribute(VERIFYCODE_IN_SESSION);
	}
	public static void putverifyCodeVo(VerifyCodeVo vo) {
		getSession().setAttribute(VERIFYCODE_IN_SESSION, vo);
	}

}







