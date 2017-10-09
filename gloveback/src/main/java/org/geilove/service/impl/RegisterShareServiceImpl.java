package org.geilove.service.impl;

import org.geilove.dao.ShareUserMapper;
import org.geilove.pojo.ShareUser;
import org.geilove.service.RegisterByShareService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Songshifeng on 2017/9/22
 */

@Service
public class RegisterShareServiceImpl implements RegisterByShareService {

    @Resource
    private ShareUserMapper shareUserMapper;


    @Override
    public int registerByShare(ShareUser shareUser) {
        return shareUserMapper.insert(shareUser);
    }
}
