package com.xmg.p2p.base.query;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.StringUtils;

import com.xmg.p2p.base.util.DateUtil;

import lombok.Getter;
import lombok.Setter;

/*
 * 登录日志的查询条件
 */
@Setter@Getter
public class IplogQuery extends QueryObject {
	
	private Date beginDate;
	private Date endDate;
	private String username;
	private int state=-1;
	private int userType;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	public void setBeginDate(Date beginDate) {
		this.beginDate=beginDate;
	}
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	public void setEndDate(Date endDate) {
		this.endDate=endDate;
	}
	
	public Date getEndDate() {
		return this.endDate==null ? null:DateUtil.endOfDay(endDate);
	}
	
	public String getUsername() {
		return StringUtils.hasLength(username) ?username:null;
	}
}
