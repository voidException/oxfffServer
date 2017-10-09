package org.geilove.requestParam;
//专门为公益排行榜做的请求参数
public class DonaterListParam {

	 private Integer  page;
	 private Integer  pageSize;
	 private Integer  money;//用户捐钱的多少

	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getMoney() {
		return money;
	}
	public void setMoney(Integer money) {
		this.money = money;
	}
}
