package org.geilove.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
/**
 * Created by mfhj-dz-001-424 on 17/2/18.
 */
@Controller
@RequestMapping(value="/backPages")
public class JspAdminController {
    @RequestMapping(value="/checkProfileList",method = RequestMethod.GET)
    public String checkProfiles(){
        String index="backSite/checkProfileList";
        return index;
    }
}
