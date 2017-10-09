package org.geilove.response;

import java.util.Date;
import java.util.List;
import org.geilove.vo.ItemFull;


public class ItemListRsp {
	private List<ItemFull> lp;
	
	private Integer retcode ;
	
	private String msg;

	public List<ItemFull> getLp() {
		return lp;
	}

	public void setLp(List<ItemFull> lp) {
		this.lp = lp;
	}

	public Integer getRetcode() {
		return retcode;
	}

	public void setRetcode(Integer retcode) {
		this.retcode = retcode;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
