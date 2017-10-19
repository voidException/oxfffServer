package org.geilove.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
/**
 * 葡萄互助的后台管理系统路由
 */
@Controller
@RequestMapping(value="/admin")

public class JspPageHelpController {

    @RequestMapping(value="/login.do",method = RequestMethod.GET)
    public String checkProfiles(){
        String index="putaohelp/loginhelp";
        return index;
    }
}
