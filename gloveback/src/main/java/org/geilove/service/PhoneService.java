package org.geilove.service;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import org.geilove.pojo.Message;
import org.geilove.response.CommonRsp;

/**
 * Created by aihaitao on 7/11/2017.
 */
public interface PhoneService {

  public Message checkPhoneCode(String phone, String code);

  public CommonRsp phoneRegister(String phone, String userPassword, String verifycode,String shareUserUUID);

  public    CommonRsp getVerifyCode(String  phone)throws ClientException;

}
