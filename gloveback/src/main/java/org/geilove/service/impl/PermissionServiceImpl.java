package org.geilove.service.impl;

import org.geilove.service.PermissionService;
import org.springframework.stereotype.Service;

/**
 * Created by aihaitao on 23/1/2018.
 */
@Service("Permission")
public class PermissionServiceImpl implements PermissionService {
    @Override
    public Boolean checkPer(String userToken,String  resourceNum){



        return true;
    }

}
