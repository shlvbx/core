package com.xmg.p2p.base.query;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public abstract class QueryObject implements Serializable {
	
	private Integer currentPage=1;//当前页
	private Integer pageSize=5;//每页的长度
	
	public int getStart() {
		return (currentPage - 1) * pageSize;
	}
}
