package org.geilove.controller;

import org.geilove.dao.*;
import org.geilove.pojo.Putaoauth;
import org.geilove.util.Response;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
/*
* 提供权限校验，角色创建，删除等功能
*
*/

@Controller
@RequestMapping("/grapeAdmin")
public class PermissionController {

    @Resource
    private T_userDao tUserDao;
    @Resource
    private ResourceMapper  resourceMapper ;
    @Resource
    private RoleMapper roleMapper;
    @Resource
    private UserRoleMapper userRoleMapper;
    @Resource
    private RoleResourceMapper roleResourceMapper;

    @RequestMapping("/checkPermission.do")
    @ResponseBody
    public Object  checkPermission (HttpServletRequest request ) {// 检查用户是否有操作该资源的权限
        Response<String> resp = new Response<>(); //
        String  token=request.getParameter("token");
        String  resourceID=request.getParameter("resourceID");

        return resp;
    }

}




















