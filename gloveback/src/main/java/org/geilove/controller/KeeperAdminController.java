package org.geilove.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
/**
 * 用于互助管家包含逻辑处理的后台
 */
@Controller
@RequestMapping(value="/backPages")
public class KeeperAdminController {
    @RequestMapping(value="/checkProfileList",method = RequestMethod.GET)
    public String checkProfiles(){
        String index="backSite/checkProfileList";
        return index;
    }
}
