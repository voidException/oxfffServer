package org.geilove.controller;
import org.geilove.pojo.News;
import org.geilove.response.NewssRsp;
import org.geilove.service.PutaoFindService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 葡萄互助发现页面
 */
@Controller
@RequestMapping("/find")
public class PutaoFindController {
    @Resource
    private PutaoFindService putaoFindService;

    @RequestMapping(value="/newslist.do",method= RequestMethod.POST)
    @ResponseBody
    public  Object getNewsList(HttpServletRequest request){
        NewssRsp newssRsp=new NewssRsp();
//        String publishdate=request.getParameter("publishdate"); //时间
        String pageStr=request.getParameter("page"); //时间
        int page=Integer.valueOf(pageStr);
        int pageSize=200;
        page=pageSize*(page-1);
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("page",page);
        map.put("pageSize",pageSize);
        List<News> newss;
        try{
            newss=putaoFindService.getNewss(map);
            if (newss!=null){
                newssRsp.setResult(newss);
                newssRsp.setRetcode(2000);
                newssRsp.setMsg("成功");
                return newssRsp;
            }
        }catch (Exception e){
             newssRsp.setMsg("获取新闻列表出现异常");
             newssRsp.setRetcode(2001);
             return newssRsp;
        }

        newssRsp.setMsg("新闻数据为空");
        newssRsp.setRetcode(2001);
        return  newssRsp;
    }
}
