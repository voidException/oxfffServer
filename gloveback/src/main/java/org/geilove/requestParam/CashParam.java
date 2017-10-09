package org.geilove.requestParam;

public class CashParam {
	
	private String proof;
	
	private Long cashid; //应该是Long类型，受限于tweet表

	public String getProof() {
		return proof;
	}

	public void setProof(String proof) {
		this.proof = proof;
	}

	public Long getCashid() {
		return cashid;
	}

	public void setCashid(Long cashid) {
		this.cashid = cashid;
	}
}
