package com.xmg.p2p.base.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xmg.p2p.base.domain.Logininfo;

public interface logininfoMapper {
   
    int insert(Logininfo record);
    Logininfo selectByPrimaryKey(Long id);
    List<Logininfo> selectAll();
    
    int updateByPrimaryKey(Logininfo record);

	int countUserByName(String username);
	/**
	 * 用户的登录检查
	 * @param username
	 * @param password
	 * @return
	 */
	Logininfo login(@Param("username")String username,
					@Param("password") String password,
					@Param("userType") int userType);
	
	int countUserByUserType(int userManager);
}