package org.geilove.service;

import org.geilove.pojo.News;

import java.util.List;
import java.util.Map;

/**
 * Created by aihaitao on 2/11/2017.
 */
public interface PutaoFindService {
    public List<News> getNewss(Map<String,Object> map);
}
