package org.geilove.controller;

/**
 * 葡萄互助的实名认证，获取用户提交的认证信息
 */
import org.geilove.dao.PutaoauthMapper;
import org.geilove.dao.UserMapper;
import org.geilove.dao.UserStaffMapper;
import org.geilove.pojo.*;

import org.geilove.requestParam.ConfirmStuffsParam;
import org.geilove.requestParam.StuffsUserUUID;
import org.geilove.response.CommonRsp;
import org.geilove.service.*;
import org.geilove.util.*;
import org.geilove.vov.CompanyBaseInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

@Controller
@RequestMapping("/grape")
public class PutaoShiMingController {

    @Resource
    private RegisterLoginService rlService;
    @Resource
    private CompanyputaoService  companyputaoService;
    @Resource
    private AshipService  ashipService;
    @Resource
    private PhoneService phoneService;
    @Resource
    private UserStaffMapper  userStaffMapper;
    @Resource
    private UserMapper userMapper;

    @Resource
    private PutaoauthMapper  putaoauthMapper;


    //7.获取公司实名认证提交的信息
    @RequestMapping(value="/getShimingInfo.do",method=RequestMethod.POST)
    @ResponseBody
    public Object renZheng( HttpServletRequest request) throws IOException {
        Response<Putaoauth> resp = new Response<Putaoauth>();
        String token=request.getParameter("token");
        String useruuid=request.getParameter("useruuid");

        String userPassword=token.substring(0,32); //token是password和userID拼接成的。
        String useridStr=token.substring(32);
        Long userid=Long.valueOf(useridStr).longValue();
        String passwdTrue=null;
        try{
            passwdTrue=rlService.selectMD5Password(Long.valueOf(userid));
        }catch (Exception e){
            resp.failByException();
            return resp;
        }
        if(!userPassword.equals(passwdTrue)){
            resp.failByNoInputData("密码不对");
            return resp;
        }
        // 这个是
        Putaoauth putaoauth=null;
        try{
            putaoauth=putaoauthMapper.selectByUserUUID(useruuid);
            if (putaoauth==null){
                resp.failByNoInputData("暂无用户提交的数据");
                return resp;
            }
        }catch (Exception e){
            resp.failByException();
            return resp; //
        }
        if (putaoauth!=null){
            //把图片地址拼装成一起返回
            String imageOne=putaoauth.getImgone();
            String imageOneUrl="http://putaohuzhu.cn/staticImage"+imageOne ;

            String imagetwo=putaoauth.getImgtwo();
            String imagetwoUrl="http://putaohuzhu.cn/staticImage"+imagetwo;

            String  imagethree=putaoauth.getImgthree();
            String  imagethreeUrl="http://putaohuzhu.cn/staticImage"+imagethree;

            putaoauth.setImgone(imageOneUrl);
            putaoauth.setImgtwo(imagetwoUrl);
            putaoauth.setImgthree(imagethreeUrl);
            resp.success(putaoauth);
            return  resp;

        }
        resp.failByNoInputData("数据为空");
        return  resp;
    }

}























































