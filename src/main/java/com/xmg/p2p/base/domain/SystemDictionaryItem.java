package com.xmg.p2p.base.domain;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class SystemDictionaryItem extends BaseDomain {
	
	private Long parentId;//数据字典明细对应的分类id
	private String title;//数据字典明细显示名称
	private int sequence;//数据字典明细在该分类中的排序
	public String  getJsonString(){
		Map<String ,Object> json = new HashMap<>();
		json.put("id", id);
		json.put("parentId", parentId);
		json.put("title", title);
		json.put("sequence", sequence);
		
		return JSONObject.toJSONString(json);
	}
	
}
