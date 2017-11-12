package org.geilove.service;


import org.geilove.pojo.RedMoney;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/*
* 红包在注册时产生unactive，充值时或者跑定时任务激活红包，使用完或者定时任务过期后失效。
* */

public interface SelRedMoneyService {

    public List<RedMoney> selAllRedMoney(HashMap<String,Object> hashMap);

    public RedMoney selectByPrimaryKey(Long redmoneyid);

    public int updateByPrimaryKeySelective(RedMoney redMoney);

    public int insertRedMoneyByShare(RedMoney redMoney);

    public void bornRedMony( String  shareUserUUID, String  newUserUUID);

}
