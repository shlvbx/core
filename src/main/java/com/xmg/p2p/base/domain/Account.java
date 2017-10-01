package com.xmg.p2p.base.domain;

import java.math.BigDecimal;


import com.xmg.p2p.base.util.BigConst;

import lombok.Getter;
import lombok.Setter;
/**
 * 用户的账户信息
 * @author Administrator
 *
 */
@Setter
@Getter
public class Account extends BaseDomain{
	
	private int version;//版本
	private String tradePassword;//交易密码
	private BigDecimal usableAmount=BigConst.ZERO;//账户可用余额
	private BigDecimal freezedAmount=BigConst.ZERO;//账户冻结金额
	private BigDecimal unReceiveInterest=BigConst.ZERO;//账户待收利息
	private BigDecimal unReceivePrincipal=BigConst.ZERO;//账户待收本金
	private BigDecimal unReturnAmount=BigConst.ZERO;//账户待还金额
	private BigDecimal remainBorrowLimit=BigConst.ZERO;//账户剩余授信额度
	private BigDecimal borrowLimit=BigConst.ZERO;//账户授信额度
	
	//总额
	public BigDecimal getTotalAmount(){
		
		return this.usableAmount.add(this.freezedAmount).add(this.unReceivePrincipal);
	}
}
