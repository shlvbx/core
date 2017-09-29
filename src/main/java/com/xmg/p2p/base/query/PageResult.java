package com.xmg.p2p.base.query;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

/**
 * 分页结果封装
 * @author Administrator
 *
 */
@Getter
public class PageResult {
	
	private Integer currentPage=1;//当前页
	private Integer pageSize=10;//每页的记录数
	
	private List listData;//当前页的结果集
	private Integer totalCount;//总记录数
	
	private Integer totalPage;//总页数
	
	private Integer prePage;//上一页
	private Integer nextPage;//下一页
	
	//如果totalPage为0返回页数为第一页
	public int getTotalPage() {
		return totalPage==0 ? 1:totalPage;
	}
	
	public static PageResult empty(Integer pageSize) {
		return new PageResult(pageSize, new ArrayList<>(), 0, 1);
	}
	//给totalPage,prePage,nextPage赋值
	public PageResult(Integer pageSize, List listData, Integer totalCount, Integer currentPage) {
		super();
		this.pageSize = pageSize;
		this.listData = listData;
		this.totalCount = totalCount;
		this.currentPage=currentPage;
		this.totalPage=this.totalCount % this.pageSize==0 ? this.totalCount
					/this.pageSize : this.totalCount/this.pageSize;
		this.prePage=this.currentPage-1>=1 ? this.currentPage-1:1;
		this.nextPage=(this.currentPage+1)<=this.totalPage ? this.currentPage+1 : this.totalPage;
		
	}
	
}
