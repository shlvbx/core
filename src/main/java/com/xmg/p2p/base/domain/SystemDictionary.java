package com.xmg.p2p.base.domain;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

import lombok.Getter;
import lombok.Setter;

/*
 * 数据字典分类
 */
@Setter @Getter
public class SystemDictionary extends BaseDomain {

	private String sn;//数据字典分类编码
	private String title;//数据字典分类名称
	
	public String getJsonString(){
		Map<String,Object> map=new HashMap<>();
		map.put("id", id);
		map.put("sn", sn);
		map.put("title", title);
		return JSONObject.toJSONString(map);
	}
}
