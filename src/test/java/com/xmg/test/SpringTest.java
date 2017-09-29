package com.xmg.test;

import java.io.IOException;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xmg.p2p.base.domain.Logininfo;
import com.xmg.p2p.base.mapper.logininfoMapper;
import com.xmg.p2p.base.service.ILogininfoService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class SpringTest {
	@Autowired
	private ILogininfoService service;
	
	@Autowired
	private logininfoMapper loginfoMapper;	
	@Test
	public void test01(){
		service.register("1111", "1111");
	}
	@Test
	public void test02() throws IOException{
		
		
		Logininfo record=new Logininfo();
		record.setUsername("lisi");
		loginfoMapper.insert(record);
		System.out.println(record.getId());
		System.out.println(record);
	}
}
