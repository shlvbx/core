package com.xmg.p2p.base.service.impl;

import java.util.Date;
import java.util.Properties;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.xmg.p2p.base.domain.MailVerify;
import com.xmg.p2p.base.domain.Userinfo;
import com.xmg.p2p.base.mapper.MailVerifyMapper;
import com.xmg.p2p.base.service.ISendEmailService;
import com.xmg.p2p.base.service.IUserinfosService;
import com.xmg.p2p.base.util.BigConst;
import com.xmg.p2p.base.util.UserContext;

@Service
public class SendEmailServiceImpl implements ISendEmailService {

	@Value("${mail.applicationUrl}")
	private String mailUrl;
	@Value("${mail.host}")
	private String mailHost;
	@Value("${mail.username}")
	private String mailUsername;
	@Value("${mail.password}")
	private String mailPassword;
	
	@Autowired
	private IUserinfosService userinfoService;
	
	private MailVerifyMapper mailVerifyMapper;
	@Override
	public void sendEmail(String email) {
		
		//判断用户是否绑定邮箱
		long id=UserContext.getCurrent().getId();
		Userinfo info=this.userinfoService.get(id);
		if(!info.getIsBindEmail()) {
			//构造一份邮件
			String uuid=UUID.randomUUID().toString();
			StringBuilder sb=new StringBuilder(100);
			sb.append("p2p验证邮箱<a href='").append(this.mailUrl).
			append("/bindEmsil.action").append("?uuid=").append(uuid).
			append("'>点击</a>, 有效期是").append(BigConst.VERIFYCODEtTIME).append("天");
			
			try {
				this.sendMail(email,sb.toString());
				//mail的构建
				MailVerify mv=new MailVerify();
				mv.setLogininfoId(id);
				mv.setEmail(email);
				mv.setUuid(uuid);
				mv.setSendEmailTime(new Date());
				
				this.mailVerifyMapper.insert(mv);
			} catch (MessagingException e) {
				throw new RuntimeException("发送失败");
			}
			
		}else {
			throw new RuntimeException("该邮箱已经被绑定");
		}
	}

	//发送邮件方法 的抽取
		private void sendMail(String email, String content)
				throws MessagingException {
			//发送html邮件
			JavaMailSenderImpl sender = new JavaMailSenderImpl() ;
			//设置服务器地址
			sender.setHost(mailHost);
			//创建一个邮件消息 
			MimeMessage msg = sender.createMimeMessage() ;
			MimeMessageHelper msgHelper = new MimeMessageHelper(msg, "utf-8"); 
			//设置收件人 寄件人 等邮件相关
			msgHelper.setTo(email);
			msgHelper.setFrom(mailUsername);
			msgHelper.setSubject("绑定邮箱验证邮件");
			//设置邮件内容
			msgHelper.setText(content.toString(),true); //true 表示启动html格式的邮件
			//设置发送准备
			sender.setUsername(mailUsername);
			sender.setPassword(mailPassword);
			
			Properties prop =  new Properties() ;
			prop.put("mail.smtp.auth",true) ; //设置为true 让服务器进行认证账户和面是否正确
			prop.put("mail.smtp.timeout", "25000") ;
			sender.setJavaMailProperties(prop);
			//发送邮件 
			sender.send(msg);
		}


}
