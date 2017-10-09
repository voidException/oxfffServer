package org.geilove.service;


import org.geilove.pojo.RedMoney;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

public interface SelRedMoneyService {

    public List<RedMoney> selAllRedMoney(HashMap<String,Object> hashMap);

    public RedMoney selectByPrimaryKey(Long redmoneyid);

    public int updateByPrimaryKeySelective(RedMoney redMoney);

    public int insertRedMoneyByShare(RedMoney redMoney);
}
