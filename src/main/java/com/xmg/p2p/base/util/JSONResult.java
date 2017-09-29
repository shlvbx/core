package com.xmg.p2p.base.util;

import lombok.Getter;
import lombok.Setter;


@Setter @Getter
public class JSONResult {

	private boolean success = true ;
	private String msg;
	
	public JSONResult(boolean success, String msg) {
		super();
		this.success = success;
		this.msg = msg;
	}

	public JSONResult(String msg) { 
		super();
		this.msg = msg;
	}

	public JSONResult() {
		super();
	}
													
	
}
