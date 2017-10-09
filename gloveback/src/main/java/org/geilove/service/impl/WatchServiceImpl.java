package org.geilove.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import org.geilove.pojo.DoubleFans;
import org.geilove.service.WatchService;
import org.geilove.sqlpojo.PartHelpPojo;
import org.geilove.sqlpojo.PartWatchPojo;
import org.geilove.dao.DoubleFansMapper;
import org.springframework.stereotype.Service;
@Service("dowatch")
public class WatchServiceImpl implements WatchService{
	
    private Integer watchtag;
	@Resource
	private DoubleFansMapper dbMapper;
	
	@Override
	public Integer doWatch(DoubleFans dbfans){
		watchtag=dbMapper.insert(dbfans);
		//这里应该检查是否已经关注了，非常重要
		return watchtag;
	}
	
	@Override
	public List<PartWatchPojo> getPartWatchInfo(Map<String,Object> map){
		List<PartWatchPojo> lp=dbMapper.getPartWatchProfile(map);
		return lp;
	}


	//该方法实现是错误的，应该没有类使用
	@Override
	public List<PartHelpPojo> getWatchList(Map<String,Object> map){
		List<PartHelpPojo> fansList=new ArrayList<PartHelpPojo>();
		fansList=dbMapper.selectHelpMen(map);
		return fansList;
	}

    //查询一个人是否关注另一个人
	@Override
	public PartWatchPojo  watchMayNot (Map<String,Object> map ){
		PartWatchPojo partWatchPojo ;
		partWatchPojo=dbMapper.watchOrNot(map);
		return  partWatchPojo;
	}
}
