package org.geilove.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by mfhj-dz-001-424 on 17/2/18.
 */
@Controller
@RequestMapping(value="/url")
public class WxController {
    @RequestMapping(value="/token.do",method = RequestMethod.GET)
    public void checkProfiles(HttpServletRequest  request,HttpServletResponse response)throws ServletException, IOException {
        System.out.println("wxToken");
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");

        System.out.println("signature:" + signature);
        System.out.println("timestamp:" + timestamp);
        System.out.println("nonce:" + nonce);
        System.out.println("echostr:" + echostr);
        PrintWriter pw = response.getWriter();
        pw.append(echostr);
        pw.flush();

    }
}
