package org.geilove.service.impl;

import org.geilove.dao.NewsMapper;
import org.geilove.pojo.News;
import org.geilove.service.PutaoFindService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by aihaitao on 2/11/2017.
 */
@Service("PutaoFind")
public class PutaoFindServiceImpl implements PutaoFindService {

    @Resource
    private NewsMapper  newsMapper;

    @Override
    public List<News> getNewss(Map<String,Object>  map){
        List<News> newss=newsMapper.getNewss(map);
        return  newss;
    }

}
