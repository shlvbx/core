package com.xmg.p2p.base.service.impl;

import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import com.xmg.p2p.base.domain.VerifyCodeVo;
import com.xmg.p2p.base.service.ISendVerifyCode;
import com.xmg.p2p.base.util.BigConst;
import com.xmg.p2p.base.util.DateUtil;
import com.xmg.p2p.base.util.UserContext;
/*
 * 手机验证码校验
 */
@Service
public class SendVerifyCodeImpl implements ISendVerifyCode {
	/*sms.username=xmg
			sms.password=1111
			sms.apikey=1111
			sms.url=http://localhost:8082/send.do
*/				
	@Value("${sms.username}")
	private String smsUserName;
	@Value("${sms.password}")
	private String smsPassword;
	@Value("${sms.apikey}")
	private String smsapikey;
	@Value("${sms.url}")
	private String smsUrl;
	
	@Override
	public void sendVerifyCode(String phoneNumber)  {
		VerifyCodeVo vc=UserContext.getVerifyCodeVo();
		if(vc==null||
				(vc!=null&&
							DateUtil.getBetweenSecond(new Date(), vc.getSendTime())>=BigConst.VERIFYCODEtTIME)) {
			String code=UUID.randomUUID().toString().substring(0, 4);
			//创建一个人url
			try {
				URL url=new URL(this.smsUrl);
				HttpURLConnection conn=(HttpURLConnection) url.openConnection();
				conn.setDoOutput(true);
				conn.setRequestMethod("POST");
				
				StringBuilder params = new StringBuilder(100)
						.append("username=").append(smsUserName)
						.append("&password=").append(smsPassword)
						.append("&apikey=").append(smsapikey).append("&mobile=")
						.append(phoneNumber).append("&content=")
						.append("您的验证码是:").append(code).append(",有效时间是:")
						.append(BigConst.VERIFYCODE_VALID_TIME).append("秒!");
				
				// 写入参数
				conn.getOutputStream().write(params.toString().getBytes());
				// 读入响应
				String response = StreamUtils.copyToString(
						conn.getInputStream(), Charset.forName("UTF-8"));
				
				if(response.startsWith("success")) {
					vc=new VerifyCodeVo(phoneNumber, code, new Date());
					UserContext.putverifyCodeVo(vc);
				}else {
					throw new RuntimeException("请过一段时间再发");
				}
			} catch (Exception e) {
				/*vc=new VerifyCodeVo(phoneNumber, code, new Date());
				UserContext.putverifyCodeVo(vc);*/
				throw new RuntimeException("短信发送失败");
			}
		}else {
			throw new RuntimeException("发送过于频繁");
		}
	}
	
	/*
	 * 校验验证码
	 * 
	 */
	public boolean validate(String phoneNumber,String code) {
		VerifyCodeVo vo = UserContext.getVerifyCodeVo();
		return vo!=null&&
				vo.getCode().equalsIgnoreCase(code)&&
				vo.getPhoneNumber().equals(phoneNumber)&&
				(DateUtil.getBetweenSecond(new Date(), vo.getSendTime())<=BigConst.VERIFYCODEtTIME);
	}
}
