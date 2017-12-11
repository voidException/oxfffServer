package org.geilove.service.impl;

import org.geilove.dao.*;
import org.geilove.pojo.*;
import org.geilove.service.AlipayNotifyService;
import org.geilove.util.Arith;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * 支付宝支付异步通知，比较复杂单独写
 */
@Service("alipaynotify")
public class AlipayNotifyServiceImpl implements AlipayNotifyService {

    @Resource
    private PayMoneyMapper payMoneyMapper;
    @Resource
    private PayaddsetMapper payaddsetMapper;
    @Resource
    private UserAccountMapper userAccountMapper;
    @Resource
    private ShareUserMapper shareUserMapper;
    @Resource
    private RedMoneyMapper redMoneyMapper;
    @Resource
    private AccountMapper accountMapper;
    @Resource
    private CompanyputaoMapper companyputaoMapper;
    @Resource
    private NotificationMapper notificationMapper;

    //支付宝支付异步通知
    @Override
    public  void   aliNotify(PayMoney payMoney){
        //在这里先查询是否有该订单，如果有就更新PayMoney表，对于UserAccount表如果用户没加入就插入记录，否则就只更新金钱
        PayMoney  payMoney1=new PayMoney();
        if (false){
            //判断app_id 或者seller_id正确
        }

        Map<String,Object> map=new HashMap<String,Object>();
        map.put("outTradeNo",payMoney.getOutTradeNo());  //交易订单号
        map.put("totalAmount",payMoney.getTotalAmount()); //金额
        try {
            payMoney1=payMoneyMapper.selectByMapKeys(map);//查询数据库确定是否有此订单
            if (payMoney1==null){
                return;
            }
        }catch (Exception e){
            return;
        }
        //有此订单，更新PayMoney表，
        Long paymoneyid=payMoney1.getPaymoneyid();
        payMoney.setPaymoneyid(paymoneyid);
        try {
            //在生成订单的时候，已经插入了一次的。
            int updateTag=payMoneyMapper.updateByPrimaryKeySelective(payMoney); //更新充值记录表
        }catch (Exception e){
            //记录日志
        }

        //在这里，对红包表进行查询，激活红包
        try{
            String  userUUID=payMoney.getUseruuid();
            RedMoney redMoney=redMoneyMapper.selectByUserUUIDClick(userUUID);
            if (redMoney!=null && "unactive".equals(redMoney.getRedmoneystate())){
                redMoney.setRedmoneystate("active");
                redMoneyMapper.updateByPrimaryKeySelective(redMoney);
            }
        }catch (Exception e){
            //记录日志
        }

        //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
        //根据公共回传参数passback_params 判断是哪种类型的充值，更新相应的表，最后统一判断是否要激活红包
        //#############$$$$$$$$$$$**********%%%%%%%
        String passback_params=payMoney.getPassbackParams();

        if (passback_params.equals("many")){
            //out_trade_no
            String out_trade_no=payMoney.getOutTradeNo();
            //categorytype
            String categorytype=payMoney.getCategorytype();
            Map<String,Object> map1=new HashMap<>();
            map1.put("out_trade_no",out_trade_no); //商户订单号
            map1.put("categorytype",categorytype); // 交易的金额
            List<Payaddset>  payaddsets=new ArrayList<>();
            try{
                  payaddsets=payaddsetMapper.getPayaddsets(map1);
                //System.out.println(payaddsets);
            }catch (Exception e){
               //记录日志
            }
            //payaddsets 的人加入到useraccount表中，未加入的要加入，加入的就更新金额
            Map<String,Object> map2=new HashMap<>();

            for (Payaddset pas:payaddsets){
                String  userUUID=pas.getUseruuid(); //用户从属的账号
                String  accountUUID =pas.getAccountuuid();  //用户的身份证号
                String  helpType=pas.getCategorytype();   //互助的类别
                String  userName=pas.getPassbackParams(); //被充值用户的姓名
                String  totalAmount=pas.getTotalAmount(); //金额
                String  useraccountuuid=pas.getPayadduuid();
                //map2.put("userUUID",userUUID);
                map2.put("accountUUID",accountUUID);
                map2.put("categorytype",helpType);

                UserAccount  userAccount0=null;
                try {
                    userAccount0=userAccountMapper.selectByUUID(map2); //如果userAccount==null就插入新的,表明是第一次充值
                }catch (Exception e){

                }
                if (userAccount0==null){
                    // 必须new
                    userAccount0=new UserAccount();//生成预付订单的时候，不会更改useraccount表或者companyputao表，因为用户可能取消支付

                    //生效时间
                    Date effectiveDate=new  Date();//取时间
                    Calendar calendar   =   new   GregorianCalendar();
                    calendar.setTime(effectiveDate);
                    calendar.add(calendar.DAY_OF_MONTH, 180);//日期往后增加180天
                    effectiveDate=calendar.getTime();   // 生效时间

                    //把所有的字段放入到userAccount
                    // userAccount.setAccountuuid(useraccountuuid);
                    userAccount0.setUseraccountuuid(useraccountuuid); //uuid生成器生成的
                    userAccount0.setUseruuid(userUUID);
                    userAccount0.setAccountuuid(accountUUID);//accountUUID 是account的身份证号
                    userAccount0.setUsername(userName);
                    userAccount0.setBreakif("no"); //no 代表没有解除关联
                    userAccount0.setBuildrelationdate(new Date());
                    userAccount0.setBreakrelationdescription("home"); //需要修改
                    userAccount0.setCategorytype(payMoney.getCategorytype()); //互助类别
                    userAccount0.setJoindate(new Date()); //加入
                    userAccount0.setEffectivedate(effectiveDate); //用户生效时间
                    userAccount0.setNowstate("normal"); //正常
                    userAccount0.setPaytotalmoney(totalAmount.toString()); //用户第一次加入


                    try {
                        int insertTag=userAccountMapper.insertSelective(userAccount0);
                        System.out.println(insertTag);
                    }catch (Exception e){
                        System.out.println("加入计划失败");
                    }
                    //加入计划成功之后，将用户添加到Account表中
                    Account account=new Account();
                    account.setAccountuuid(accountUUID);
                    account.setUseridentity(accountUUID);
                    Account queryAccout=accountMapper.selectByuserIdentity(accountUUID);
                    if(queryAccout==null){
                        //如果account表中不存在该用户 则进行添加操作
                        account.setUsername(userName);
                        account.setAccountdate(new Date());
                        try {
                            int insertAccount=accountMapper.insertSelective(account);
                        } catch (Exception e) {
                            e.printStackTrace();
                            System.out.println("account加入失败");
                        }
                    }
                    continue;

                }//if
                else { //这种情况只当用户是充钱了
                    // 1.这个是原先数据库的钱
                    String  totalAmountStr=userAccount0.getPaytotalmoney(); //
                    Double  totalAmountDouble=Double.valueOf(totalAmountStr.toString());
                    BigDecimal totalAmountBig = new BigDecimal(String.valueOf(totalAmountDouble));
                    // 2.这个是新充值的钱数
                    String   toBeAddMoney=pas.getTotalAmount();
                    Double   alipayMoneyDouble=Double.valueOf(toBeAddMoney.toString());
                    BigDecimal   alipayMoneyBig=new BigDecimal(String.valueOf(alipayMoneyDouble));
                    // 3.钱数求和
                    BigDecimal finalMoney = alipayMoneyBig.add(totalAmountBig); //新的钱数
                    //4 .只更新钱就可以了
                    String  finalMoneyStr=finalMoney.toString();
                    userAccount0.setPaytotalmoney(finalMoneyStr);
                    try {
                        int  updateTag=userAccountMapper.updateByPrimaryKeySelective(userAccount0);
                    }catch (Exception e){
                        //记录日志
                    }
                    continue;
                }
            }//for
            return;

        }
        if (passback_params.equals("company")){ //公司充值
            //对Companyputao进行操作，先查询公司是否加入此互助计划，没有就加入，有就更新
            Map<String,Object> map4=new HashMap<>();
            String  userUUID=payMoney.getUseruuid();
            String  categorytype=payMoney.getCategorytype();//互助的类别
            map4.put("userUUID",userUUID);
            map4.put("categorytype",categorytype);
            Companyputao companyputao=null;
            try{
                 companyputao=companyputaoMapper.getCompanyputaoInfo(map4);
            }catch (Exception e){
              // 记录日志
            }
            if (companyputao==null){ //必须是有了计划才能充值，按道理永远走不到
                //用户首次加入该计划
                companyputao=new Companyputao();

                companyputao.setCompanyuuid(payMoney.getOutTradeNo());
                companyputao.setUseruuid(payMoney.getUseruuid());
                companyputao.setCreatedate(new Date());
                companyputao.setTotalmoenystr(payMoney.getTotalAmount());
                companyputao.setHelptype(payMoney.getCategorytype());
                //
                try{
                    companyputaoMapper.insertSelective(companyputao);
                }catch (Exception e){

                }
                return;
            }
            else {
                //用户已经加入该计划，需要更新钱
                // 1.这个是原先数据库的钱
                String  totalAmountStr=companyputao.getTotalmoenystr(); //
                Double  totalAmountDouble=Double.valueOf(totalAmountStr.toString());
                BigDecimal totalAmountBig = new BigDecimal(String.valueOf(totalAmountDouble));
                // 2.这个是新充值的钱数
                String   alipayMoney=payMoney.getTotalAmount();
                Double   alipayMoneyDouble=Double.valueOf(alipayMoney.toString());
                BigDecimal   alipayMoneyBig=new BigDecimal(String.valueOf(alipayMoneyDouble));
                // 3.钱数求和
                BigDecimal finalMoney = alipayMoneyBig.add(totalAmountBig); //新的钱数
                String  finalMoneyStr=finalMoney.toString();
                //只更新钱就可以了
                companyputao.setTotalmoenystr(finalMoneyStr);

                try {
                    int  updateTag=companyputaoMapper.updateByPrimaryKeySelective(companyputao);
                }catch (Exception e){

                }
                return;
            }
        }//if

        //走到这里说明是个人补充充值或者是红包充值
        // 红包充值
        String  payType=passback_params.substring(0,3); //截取前三个字符
        if (payType.equals("$$$")){//红包充值
            String str=passback_params.substring(3);
            String[] strArray=str.split("@"); //根据@分割
            String  accountuuid=strArray[0];   //
            String  accountName=strArray[1];   //
            String  userName=strArray[2];       //
            String  redMoneyuuid=strArray[3];  //红包的uuid

            // 查询确定这次红包充值是给谁充值的
            Map<String,Object> map6=new HashMap<>();
           // map6.put("userUUID",payMoney.getUseruuid());
            map6.put("accountUUID",accountuuid);
            map6.put("categorytype",payMoney.getCategorytype());
            UserAccount userAccount=null;
            try{
                userAccount=userAccountMapper.selectByaccountUUID(map6); //
            }catch (Exception e){
                //记录日志
                return;
            }
            // 1. 先查询红包是否存在
            RedMoney redMoney;
            String  moneyStr="";
            try{
                redMoney=redMoneyMapper.selectByRedUUID(redMoneyuuid); //
                if (redMoney==null){
                    return;
                }

            }catch (Exception e){
                return;
            }
            // 2.如果红包存在，并且没有被使用
            if (redMoney.getRedmoneystate().equals("active")){
                //确保红包失效
                try{
                    redMoney.setRedmoneystate("used");
                    int updateTag=redMoneyMapper.updateByPrimaryKeySelective(redMoney);
                    if (updateTag!=1){
                        moneyStr="";
                    }else{
                        moneyStr="5";
                    }
                }catch (Exception e){
                    moneyStr="";
                }

            }else{ //红包有异常，说明可能受到攻击

            }

            if (userAccount==null){ //说明用户没有加入该计划

                String  useraccountuuid= payMoney.getOutTradeNo();  //uuid
                String  useruuid=payMoney.getUseruuid();
                Date effectiveDate=new  Date();//取时间
                Calendar calendar   =   new   GregorianCalendar();
                calendar.setTime(effectiveDate);
                calendar.add(calendar.DAY_OF_MONTH, 180);//日期往后增加180天
                effectiveDate=calendar.getTime();   // 生效时间
                //把所有的字段放入到userAccount
                // 这个是钱数想加
                String finalMoneyStr=""; //最终的充值金额
                if (moneyStr.equals("")){ //这种情况对应更新红包表出现异常
                    finalMoneyStr=payMoney.getTotalAmount().toString(); //来自支付宝的金额
                }else {
                    String realMoney=payMoney.getTotalAmount().toString(); //来自支付宝的金额
                    Double moneyD= Arith.add(Double.valueOf(realMoney),new Double(5)); //
                    finalMoneyStr=moneyD.toString();
                }
                userAccount=new UserAccount();//生成预付订单的时候，不会更改useraccount表或者companyputao表，因为用户可能取消支付
                userAccount.setUseraccountuuid(useraccountuuid); //out_trade_no
                userAccount.setUseruuid(useruuid);
                userAccount.setAccountuuid(accountuuid);//accountUUID 是account的身份证号
                userAccount.setUsername(accountName);
                userAccount.setBreakif("no"); //no 代表没有解除关联
                userAccount.setBuildrelationdate(new Date());
                userAccount.setBreakrelationdescription("home");
                userAccount.setCategorytype(payMoney.getCategorytype()); //互助类别
                userAccount.setJoindate(new Date()); //加入
                userAccount.setEffectivedate(effectiveDate); //用户生效时间
                userAccount.setNowstate("normal"); //正常
                userAccount.setPaytotalmoney(finalMoneyStr);
                try {
                    int insertTag=userAccountMapper.insert(userAccount);
                    if (insertTag!=1){
                        return;
                    }
                }catch (Exception e){
                   return;
                }
                //加入计划成功之后，将用户添加到Account表中
                Account account=new Account();
                account.setAccountuuid(accountuuid);
                account.setUseridentity(accountuuid);
                //查询是否有这个用户
                Account queryAccout=accountMapper.selectByuserIdentity(accountuuid);
                if(queryAccout==null){
                    //如果account表中不存在该用户 则进行添加操作
                    account.setUsername(userName);
                    account.setAccountdate(new Date());
                    try {
                        int insertAccount=accountMapper.insertSelective(account);
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("account加入失败");
                    }
                }
                return;
            } else{//用户加入了互助计划
                //需要确定用户是否在此人名下，还是他人名下
                //本人名下就直接充值，在他人名下就需要发送下消息
                // 需要给加上红包的充值钱数，待做
                if (userAccount.getUseruuid().equals(payMoney.getUseruuid())){
                    //给自己名下的用户带红包补充充值
                    Double finalMoney=0.0;
                    if (moneyStr.equals("")){
                        //finalMoneyStr=payMoney.getTotalAmount().toString(); //来自支付宝的金额
                         finalMoney=Arith.add(Double.valueOf(userAccount.getPaytotalmoney()),Double.valueOf(payMoney.getTotalAmount()));
                    }else {
                        // 红包金额，支付宝金额，原先的金额三者相加
                        finalMoney=Arith.add(Double.valueOf(userAccount.getPaytotalmoney()),Double.valueOf(payMoney.getTotalAmount()));

                        finalMoney=Arith.add(finalMoney,5);
                    }
                    String  finalMoneyStr=finalMoney.toString();
                    //只更新钱就可以了
                    userAccount.setPaytotalmoney(finalMoneyStr);
                    try {
                        int  updateTag=userAccountMapper.updateByPrimaryKeySelective(userAccount);
                        if (updateTag!=1){
                            //没有更新成功，记录日志
                            return;
                        }
                    }catch (Exception e){
                        //没有更新成功，记录日志
                        return;
                    }
                    return;
                }else{ //给别人充值

                    Double finalMoney=0.0;
                    if (moneyStr.equals("")){
                        finalMoney=Arith.add(Double.valueOf(userAccount.getPaytotalmoney()),Double.valueOf(payMoney.getTotalAmount()));
                    }else {
                        // 红包金额，支付宝金额，原先的金额三者相加
                        finalMoney=Arith.add(Double.valueOf(userAccount.getPaytotalmoney()),Double.valueOf(payMoney.getTotalAmount()));
                        finalMoney=Arith.add(finalMoney,5);
                    }
                    String  finalMoneyStr=finalMoney.toString();
                    //只更新钱就可以了
                    userAccount.setPaytotalmoney(finalMoneyStr);
                    //更改用户的账户余额
                    try {
                        int  updateTag=userAccountMapper.updateByPrimaryKeySelective(userAccount);
                        if (updateTag!=1){
                            //更新不成功，记录日志
                            return;
                        }
                    }catch (Exception e){
                        //记录日志
                        return;
                    }

                    try{
                        //发送消息
                        Notification notification=new Notification();
                        notification.setNotifyuuid(UUID.randomUUID().toString());
                        notification.setUseruuid(userAccount.getUseruuid());
                        notification.setMsgtype("red"); //红包消息
                        notification.setNotifytime(new Date());
                        notification.setMessage(userName+"给用户"+accountName+"使用红包5元");
                        notificationMapper.insert(notification);

                    }catch (Exception e){
                        //记录日志
                        return;
                    }

                    return;
                }

            }
        }else if (payType.equals("@@@")){ //个人

            //*********更新UserAccount表*********//
            // 1.根据userUUID，accountUUID,categorytype查询是否有此用户参与的该计划，如果没有就加入，如果有就更新
            String str=passback_params.substring(3);
            String[] strArray=str.split("@"); //根据@分割
            String  accountUUID=strArray[0];   //
            String  userName=strArray[1];       //
            String  userUUID=payMoney.getUseruuid();
            String  categorytype=payMoney.getCategorytype();//互助的类别
            String  totalAmount=payMoney.getTotalAmount(); //

              Map<String,Object> map3=new HashMap<>();
//            map3.put("userUUID",userUUID);
            map3.put("accountUUID",accountUUID);
            map3.put("categorytype",categorytype);
            UserAccount userAccount=null;
            try {
                userAccount=userAccountMapper.selectByUUID(map3); //如果userAccount==null就插入新的,表明是第一次充值
            }catch (Exception e){
            }
            if (userAccount==null){ //正常不会走这一步，补充充值时用户肯定是存在的
                // 必须new
                userAccount=new UserAccount();//生成预付订单的时候，不会更改useraccount表或者companyputao表，因为用户可能取消支付
                String  useraccountuuid= payMoney.getOutTradeNo();  //uuid

                Date effectiveDate=new  Date();//取时间
                Calendar calendar   =   new   GregorianCalendar();
                calendar.setTime(effectiveDate);
                calendar.add(calendar.DAY_OF_MONTH, 180);//日期往后增加180天
                effectiveDate=calendar.getTime();   // 生效时间

                //把所有的字段放入到userAccount
                // userAccount.setAccountuuid(useraccountuuid);
                userAccount.setUseraccountuuid(useraccountuuid); //out_trade_no
                userAccount.setUseruuid(userUUID);
                userAccount.setAccountuuid(accountUUID);//accountUUID 是account的身份证号
                userAccount.setUsername(userName);
                userAccount.setBreakif("no"); //no 代表没有解除关联
                userAccount.setBuildrelationdate(new Date());
                userAccount.setBreakrelationdescription("home"); //需要修改
                userAccount.setBuildrelationdescription("home");
                userAccount.setCategorytype(payMoney.getCategorytype()); //互助类别
                userAccount.setJoindate(new Date()); //加入
                userAccount.setEffectivedate(effectiveDate); //用户生效时间
                userAccount.setNowstate("normal"); //正常
                userAccount.setPaytotalmoney(totalAmount.toString());
                //userAccount.setPaytotalmoney(totalAmount); //账户余额

                try {
                    int insertTag=userAccountMapper.insertSelective(userAccount);
                }catch (Exception e){

                }
                //加入计划成功之后，将用户添加到Account表中
                Account account=new Account();
                account.setAccountuuid(accountUUID);
                account.setUseridentity(accountUUID);
                Account queryAccout=accountMapper.selectByuserIdentity(accountUUID);
                if(queryAccout==null){
                    //如果account表中不存在该用户 则进行添加操作
                    account.setUsername(userName);
                    account.setAccountdate(new Date());
                    try {
                        int insertAccount=accountMapper.insertSelective(account);
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("account加入失败");
                    }
                }
                return;
            }else { //这种情况是用户给家人补充充值
                // 1.这个是原先数据库的钱
                String  totalAmountStr=userAccount.getPaytotalmoney(); //
                Double  totalAmountDouble=Double.valueOf(totalAmountStr.toString());
                BigDecimal totalAmountBig = new BigDecimal(String.valueOf(totalAmountDouble));
                // 2.这个是新充值的钱数
                String   alipayMoney=payMoney.getTotalAmount();
                Double   alipayMoneyDouble=Double.valueOf(alipayMoney.toString());
                BigDecimal   alipayMoneyBig=new BigDecimal(String.valueOf(alipayMoneyDouble));
                // 3.钱数求和
                BigDecimal finalMoney = alipayMoneyBig.add(totalAmountBig); //新的钱数
                String  finalMoneyStr=finalMoney.toString();
                //只更新钱就可以了
                userAccount.setPaytotalmoney(finalMoneyStr);
                try {
                    int  updateTag=userAccountMapper.updateByPrimaryKeySelective(userAccount);
                }catch (Exception e){

                }
                return;
            }
        }
    }//aliNotify
}
