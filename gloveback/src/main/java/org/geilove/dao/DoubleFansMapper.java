package org.geilove.dao;

import java.util.List;
import java.util.Map;

import org.geilove.pojo.DoubleFans;
import org.geilove.sqlpojo.PartHelpPojo;
import org.geilove.sqlpojo.PartWatchPojo;
import org.geilove.vo.IwatchPeopleVo;
import org.geilove.vo.PeopleListVo;

public interface DoubleFansMapper {
    int deleteByPrimaryKey(Long doublefansid);

    int insert(DoubleFans record);

    int insertSelective(DoubleFans record);

    DoubleFans selectByPrimaryKey(Long doublefansid);

    int updateByPrimaryKeySelective(DoubleFans record);

    int updateByPrimaryKey(DoubleFans record);
    
    List<PartWatchPojo> getPartWatchProfile(Map<String,Object> map); //获取DoubleFans表中的一组数据，还没实现
    
    List<Long> getWatchids(Map<String,Object> map); //获取用户所关注人的一组id。


    List<PeopleListVo> getWatchidsListMenLoadMore(Map<String,Object> map); //获取我关注的人的id以及关注时间，查看我关注的人用
    List<IwatchPeopleVo>   getMyFansidsLoadMore(Map<String,Object> map);  //根据用户的id获得用户粉丝的ids,loadMore


    //这个实现有问题，暂时不知道什么地方用到了，以后要改
    List<PartHelpPojo> selectHelpMen(Map<String,Object> map); //自定义，获得帮助人-被帮助人关系表

    Integer cancelWatch(Map<String,Object> map); //取消关注一个人

    PartWatchPojo watchOrNot(Map<String,Object> map); //查询是否关注一个人
    
}













