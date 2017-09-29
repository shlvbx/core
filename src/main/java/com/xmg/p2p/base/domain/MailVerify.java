package com.xmg.p2p.base.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
@Setter@Getter
public class MailVerify extends BaseDomain {
	
	private String uuid;
	private String email;
	private Date sendEmailTime;
	private long logininfoId;
}
