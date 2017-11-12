package org.geilove.service.impl;

import org.geilove.dao.*;
import org.geilove.pojo.*;
import org.geilove.service.AlipayNotifyService;
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

    //支付宝支付异步通知
    @Override
    public  void   aliNotify(PayMoney payMoney){
        //在这里先查询是否有该订单，如果有就更新PayMoney表，对于UserAccount表如果用户没加入就插入记录，否则就只更新金钱
        PayMoney  payMoney1=new PayMoney();
        if (false){
            //判断app_id 或者seller_id正确
        }

        Map<String,String> map=new HashMap<String,String>();
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
            List<Payaddset>  payaddsets=new LinkedList<>();
            try{
                  payaddsets=payaddsetMapper.getPayaddsets(map1);
            }catch (Exception e){

            }
            //payaddsets 的人加入到useraccount表中，未加入的要加入，加入的就更新金额
            Map<String,Object> map2=new HashMap<>();

            for (Payaddset pas:payaddsets){
                String  userUUID=pas.getUseruuid(); //用户从属的账号
                String  accountUUID =pas.getAccountuuid();  //用户的身份证号
                String  helpType=pas.getCategorytype();   //互助的类别
                String  totalAmount=pas.getTotalAmount(); //金额
                map2.put("userUUID",userUUID);
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
                    String  useraccountuuid= UUID.randomUUID().toString();  //uuid
                    String  useruuid=payMoney.getUseruuid();
                    String  accountuuid=payMoney.getAccountuuid();
                    String  userName=payMoney.getPassbackParams().substring(18); //用户姓名

                    Date effectiveDate=new  Date();//取时间
                    System.out.println(effectiveDate.toString());
                    Calendar calendar   =   new   GregorianCalendar();
                    calendar.setTime(effectiveDate);
                    calendar.add(calendar.DAY_OF_MONTH, 180);//日期往后增加180天
                    effectiveDate=calendar.getTime();   // 生效时间
                    System.out.println(effectiveDate.toString());

                    //把所有的字段放入到userAccount
                    // userAccount.setAccountuuid(useraccountuuid);
                    userAccount0.setUseraccountuuid(useraccountuuid); //uuid生成器生成的
                    userAccount0.setUseruuid(useruuid);
                    userAccount0.setAccountuuid(accountuuid);//accountUUID 是account的身份证号
                    userAccount0.setUsername(userName);
                    userAccount0.setBreakif("no"); //no 代表没有解除关联
                    userAccount0.setBuildrelationdate(new Date());
                    userAccount0.setBreakrelationdescription("business"); //需要修改
                    userAccount0.setCategorytype(payMoney.getCategorytype()); //互助类别
                    userAccount0.setJoindate(new Date()); //加入
                    userAccount0.setEffectivedate(effectiveDate); //用户生效时间
                    userAccount0.setNowstate("normal"); //正常
                    userAccount0.setPaytotalmoney(totalAmount.toString());
                    userAccount0.setPaytotalmoney(totalAmount); //账户余额

                    try {
                        int insertTag=userAccountMapper.insertSelective(userAccount0);
                        System.out.println(insertTag);
                    }catch (Exception e){
                        System.out.println("加入计划失败");
                    }
                    //加入计划成功之后，将用户添加到Account表中
                    Account account=new Account();
                    account.setAccountuuid(accountuuid);
                    account.setUseridentity(accountuuid);
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

                }//if
                else { //这种情况只当用户是充钱了
                    // 1.这个是原先数据库的钱
                    String  totalAmountStr=userAccount0.getPaytotalmoney(); //
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
                    userAccount0.setPaytotalmoney(finalMoneyStr);
                    try {
                        int  updateTag=userAccountMapper.updateByPrimaryKeySelective(userAccount0);
                    }catch (Exception e){

                    }
                    return;

                }
            }//for

        }
        if (passback_params.equals("company")){ //公司充值
            //对Companyputao进行操作，先查询公司是否加入此互助计划，没有就加入，有就更新
            Map<String,Object> map4=new HashMap<>();

            String  userUUID=payMoney.getUseruuid();
            String  accountUUID =payMoney.getAccountuuid();
            String  categorytype=payMoney.getCategorytype();//互助的类别
            String  totalAmount=payMoney.getTotalAmount(); //
            map4.put("userUUID",userUUID);
            map4.put("categorytype",categorytype);
            Companyputao companyputao=null;
            try{
                 companyputao=companyputaoMapper.getCompanyputaoInfo(map4);
            }catch (Exception e){

            }
            if (companyputao==null){
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

        //*********更新UserAccount表*********//
        // 1.根据userUUID，accountUUID,categorytype查询是否有此用户参与的该计划，如果没有就加入，如果有就更新
        Map<String,Object> map3=new HashMap<>();

        String  userUUID=payMoney.getUseruuid();
        String  accountUUID =payMoney.getAccountuuid();
        String  categorytype=payMoney.getCategorytype();//互助的类别
        String  totalAmount=payMoney.getTotalAmount(); //
        map3.put("userUUID",userUUID);
        map3.put("accountUUID",accountUUID);
        map3.put("categorytype",categorytype);

        UserAccount userAccount=null;
        try {
            userAccount=userAccountMapper.selectByUUID(map3); //如果userAccount==null就插入新的,表明是第一次充值
        }catch (Exception e){


        }
        if (userAccount==null){
            // 必须new
            userAccount=new UserAccount();//生成预付订单的时候，不会更改useraccount表或者companyputao表，因为用户可能取消支付
            String  useraccountuuid= UUID.randomUUID().toString();  //uuid
            String  useruuid=payMoney.getUseruuid();
            String  accountuuid=payMoney.getAccountuuid();
            String  userName=payMoney.getPassbackParams().substring(18); //用户姓名

            Date effectiveDate=new  Date();//取时间
            System.out.println(effectiveDate.toString());
            Calendar calendar   =   new   GregorianCalendar();
            calendar.setTime(effectiveDate);
            calendar.add(calendar.DAY_OF_MONTH, 180);//日期往后增加180天
            effectiveDate=calendar.getTime();   // 生效时间
            System.out.println(effectiveDate.toString());

            //把所有的字段放入到userAccount
            // userAccount.setAccountuuid(useraccountuuid);
            userAccount.setUseraccountuuid(useraccountuuid); //uuid生成器生成的
            userAccount.setUseruuid(useruuid);
            userAccount.setAccountuuid(accountuuid);//accountUUID 是account的身份证号
            userAccount.setUsername(userName);
            userAccount.setBreakif("no"); //no 代表没有解除关联
            userAccount.setBuildrelationdate(new Date());
            userAccount.setBreakrelationdescription("business"); //需要修改
            userAccount.setCategorytype(payMoney.getCategorytype()); //互助类别
            userAccount.setJoindate(new Date()); //加入
            userAccount.setEffectivedate(effectiveDate); //用户生效时间
            userAccount.setNowstate("normal"); //正常
            userAccount.setPaytotalmoney(totalAmount.toString());
            userAccount.setPaytotalmoney(totalAmount); //账户余额

            try {
                int insertTag=userAccountMapper.insertSelective(userAccount);
                System.out.println(insertTag);
            }catch (Exception e){
                System.out.println("加入计划失败");
            }
            //加入计划成功之后，将用户添加到Account表中
            Account account=new Account();
            account.setAccountuuid(accountuuid);
            account.setUseridentity(accountuuid);
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
        }else { //这种情况只当用户是充钱了
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

    }//aliNotify
}
