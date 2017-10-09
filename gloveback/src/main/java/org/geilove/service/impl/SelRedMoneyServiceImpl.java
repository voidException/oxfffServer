package org.geilove.service.impl;

import org.geilove.dao.RedMoneyMapper;
import org.geilove.pojo.RedMoney;
import org.geilove.service.SelRedMoneyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
@Service
public class SelRedMoneyServiceImpl implements SelRedMoneyService {

    @Resource
    public  RedMoneyMapper redMoneyMapper;

    @Override
    public List<RedMoney> selAllRedMoney(HashMap<String,Object> hashMap) {
        List<RedMoney> redMoneyList=redMoneyMapper.selectAllUserRedMoney(hashMap);
        return redMoneyList;
    }

    /**
     * 通过id查询对应的红包信息
     * @param redmoneyid
     * @return
     */
    @Override
    public RedMoney selectByPrimaryKey(Long redmoneyid) {
        RedMoney redMoney=redMoneyMapper.selectByPrimaryKey(redmoneyid);
        return  redMoney;
    }

    @Override
    public int updateByPrimaryKeySelective(RedMoney redMoney) {
        return redMoneyMapper.updateByPrimaryKeySelective(redMoney);
    }

    @Override
    public int insertRedMoneyByShare(RedMoney redMoney) {
        return redMoneyMapper.insertSelective(redMoney);
    }
}
