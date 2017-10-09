package org.geilove.service;
import java.util.List;
import java.util.Map;

import org.geilove.pojo.DoubleFans;
import org.geilove.sqlpojo.PartHelpPojo;
import org.geilove.sqlpojo.PartWatchPojo;
public interface WatchService {
 
	public Integer doWatch(DoubleFans dbfans);
	
	List<PartWatchPojo> getPartWatchInfo(Map<String,Object> map); //获得关注人信息
	
	public List<PartHelpPojo> getWatchList(Map<String,Object> map); //这个是或得关注-被关注列表

	//以下查询是否关注一个人,1未关注，2代表关注
	public  PartWatchPojo  watchMayNot(Map<String,Object> map );
	
}
