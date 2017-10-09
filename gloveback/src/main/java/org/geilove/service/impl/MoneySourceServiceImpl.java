package org.geilove.service.impl;

import java.util.*;

import javax.annotation.Resource;

import org.apache.commons.collections.map.HashedMap;
import org.geilove.dao.*;
import org.geilove.pojo.*;
import org.geilove.requestParam.BackUpParam;
import org.geilove.response.CommonRsp;
import org.geilove.service.CashService;
import org.geilove.service.MainService;
import org.geilove.service.MoneySourceService;
import org.geilove.service.WechatLogService;
import org.geilove.sqlpojo.PartHelpPojo;
import org.geilove.sqlpojo.PartWatchPojo;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

@Service("moneysourceservice")
public class MoneySourceServiceImpl implements MoneySourceService {
	 @Resource
	 private MoneySourceMapper moneySourceMapper;
	 @Resource
     private UserMapper userMapper;
	 @Resource
	 private MoneysrcPinglunMapper moneysrcPinglunMapper;
	 @Resource
	 private HelpInfoServiceImpl helpInfoService;
	 @Resource
	 private CashMapper cashMapper;
	 @Resource
	 private DoubleFansMapper  doubleFansMapper;
	 @Resource
	 private OpenidUserMapper openidUserMapper;
	 @Resource
	 private MainService mainService;
	 @Resource
	 private WechatLogService wechatLogService;


//	 //获取我帮助的人的
//	 public   List<PartHelpPojo> getIhelpMen( Map<String,Object> map){
//		 List<PartHelpPojo> php=moneySourceMapper.selectIhelp(map);
//		 return php;
//	 }
//	 //获取帮助我的人
//     public   List<PartHelpPojo> getHelpMeMen( Map<String,Object> map){
//    	 List<PartHelpPojo> php=moneySourceMapper.selectMenHelpMe(map);
//		 return php;
//     }

	public  static   void main(String[]args){
		String uuid="a1e0c875-379a-40ad-b3d3-a17534ae92c41654727c-40f9-42ba-b8a2-d492e2a1d0ed";
		//String uuid=UUID.randomUUID().toString();
		int a=1,b=2,c=3;
		if (a==1){
			System.out.println(a);
		}

		System.out.println(uuid);
		System.out.println(uuid.length());
		System.out.println(uuid.substring(0,36));
		System.out.println(uuid.substring(36));
	}
    //如果微信通知成功了，就执行该服务中的这个方法，如果失败了就执行另一个服务删除moneySource中存在的数据，成功还是失败在Controller里面判定
	public  void  wxNotify(MoneySource  moneySource){//moneySource 中的部分数据由微信通知传递过来，更早的数据来自于预付订单生成时插入
		 System.out.print("wxNotify");
		 String attach=moneySource.getAttach(); //cashUUID+userGoodGuyUUID
		 String cashUUID=attach.substring(0,36); //cashUUID
		 String userGoodGuyUUID=attach.substring(36);//userGoodGuyUUID
		 String userUUIDBehelped=null;
		 String moneySourceUUID=moneySource.getOutTradeNo(); //moneysourceuuid
         String openId=moneySource.getOpenid(); //openId
		 //totalFee 单位是分，转化成元
		 WechatLog wechatLog=new WechatLog();  //跑出异常

		  //0.非常重要，微信可能多次通知，这里防止重复记录
		 try {
		 	MoneySource moneySource1=moneySourceMapper.selectByMoneySourceUUID(moneySourceUUID);
		 	if (moneySource1!=null){ //微信会多次通知，必须防止重复，这里必须确保moneySourceUUID唯一
		 		return; //
			}
		 }catch (Exception e){
			 wechatLog.setClassname("MoneySourceServiceImpl");
			 wechatLog.setMethodname("wxNotify");
			 wechatLog.setLog(e.getMessage());
			 wechatLog.setLogtime(new Date());
			 wechatLog.setLogdescription("moneySourceMapper.selectByMoneySourceUUID");
			 wechatLog.setOther(moneySourceUUID);
			 return;
		 }

         //捐助人的uuid
		 User donateUser=null;
		 try{
			 //通过moneySouce中的openid查询user表，看用户是否关联了邮箱，
			 donateUser=userMapper.selectByopenId(openId);
		 }catch (Exception e){
		 	 //记录日志，到数据库
			 wechatLog.setClassname("MoneySourceServiceImpl");
			 wechatLog.setMethodname("wxNotify");
			 wechatLog.setLog(e.getMessage());
			 wechatLog.setLogtime(new Date());
			 wechatLog.setLogdescription("使用openId查询user是否存在");
			 wechatLog.setOther(openId);
			 //return; //这里也应该执行插入到MoneySource表的操作
		 }

		 if(donateUser!=null  ){ //关联了邮箱，执行复杂的更新，能获取到用户的uuid
			 //  1.更新 补全moneySource表
			 String  userDonateNickName=donateUser.getUsernickname();// 获取捐钱人的昵称
			 String  userDonatePhotoUrl=donateUser.getUserphoto(); //获取用户的头像地址
			 moneySource.setHelpmanusername(userDonateNickName);
			 moneySource.setHelpmanphotourl(userDonatePhotoUrl);

			 moneySource.setCashuuid(cashUUID); //关联的cashUUID
			 moneySource.setUseruuidgoodguy(userGoodGuyUUID);//帮助人的uuid
			 moneySource.setMoneysourceuuid(moneySourceUUID); //本记录的UUID

  			 try{
				 int updateTag=moneySourceMapper.insert(moneySource); //应该是插入
				 if (updateTag!=1){
				 	return; //没有插入成功，后续操作无意义
				 }
			 }catch (Exception e){
				 //记录日志，到数据库
				 wechatLog.setClassname("MoneySourceServiceImpl");
				 wechatLog.setMethodname("wxNotify");
				 wechatLog.setLog(e.getMessage());
				 wechatLog.setLogtime(new Date());
				 wechatLog.setLogdescription("moneySourceMapper.insert");
				 wechatLog.setOther(attach);
				 return; //这里应该返回，因为这步失败，接下来的是没意义的
			 }

			 // 2.更新cash表，realCash 、被帮助的次数要更新
			 Cash  cash=new Cash();
			 try{
				 cash=cashMapper.selectByCashUUID(cashUUID);
			 }catch (Exception e){
				 wechatLog.setClassname("MoneySourceServiceImpl");
				 wechatLog.setMethodname("wxNotify");
				 wechatLog.setLog(e.getMessage());
				 wechatLog.setLogtime(new Date());
				 wechatLog.setLogdescription("cashMapper.selectByCashUUID(cashUUID)");
				 wechatLog.setOther(cashUUID);
				 //return;
			 }
             if (cash!=null){
				 int targetcash=cash.getTargetcash();
				 Byte cashOK= cash.getCashok();

				 Integer  realCash=cash.getRealcash();
				 realCash=realCash+moneySource.getMoneynum();
				 if (realCash>targetcash){
					 cashOK=2; //达到募捐金额
				 }
				 int sumBackup=cash.getSumbackup(); //支持的次数
				 sumBackup=sumBackup+1;
				 userUUIDBehelped=cash.getUseruuid(); /*----受助用户的用户的uuid-----*/
				 cash.setRealcash(realCash);
				 cash.setCashok(cashOK);
				 cash.setSumbackup(sumBackup);
				 try {
					 int cashUpdateTag=cashMapper.updateByPrimaryKeySelective(cash);
				 }catch ( Exception e){
					 wechatLog.setClassname("MoneySourceServiceImpl");
					 wechatLog.setMethodname("wxNotify");
					 wechatLog.setLog(e.getMessage());
					 wechatLog.setLogtime(new Date());
					 wechatLog.setLogdescription("cashMapper.updateByPrimaryKeySelective(cashUUID)");
					 wechatLog.setOther(cashUUID);
					 //return;
				 }
			 }

			 // 3.更新受助人接受的总钱数等表helpInfo表
			 HelpInfo helpInfo=new HelpInfo();
			 try {
				  helpInfo= helpInfoService.selectRecord(userUUIDBehelped);
			 }catch (Exception e){
				 wechatLog.setClassname("MoneySourceServiceImpl");
				 wechatLog.setMethodname("wxNotify");
				 wechatLog.setLog(e.getMessage());
				 wechatLog.setLogtime(new Date());
				 wechatLog.setLogdescription("helpInfoService.selectRecord(userUUIDBehelped)");
				 wechatLog.setOther(userUUIDBehelped);
				 //return;
			 }

			 if (helpInfo!=null){
				 Integer money=0;
				 int totalMoney;
				 if (helpInfo.getCashtwouuid()!=null  ){//达到发布2次求助信息
					 money=helpInfo.getAccept2moneymount();
					 money=money+moneySource.getMoneynum();
					 helpInfo.setAccept2moneymount(money);
				 }
				 if (helpInfo.getCashoneuuid()!=null ){
					money=helpInfo.getAcceptfirstmoneymount();
					money=money+moneySource.getMoneynum();
					helpInfo.setAcceptfirstmoneymount(money);
				 }
				 //执行更新helpInfo表
				 totalMoney=helpInfo.getDonatemount()+moneySource.getMoneynum();
				 helpInfo.setDonatemount(totalMoney);
				 try {
					 int helpInfoInsertTag=helpInfoService.updateRecord(helpInfo);
				 }catch (Exception e){
					 wechatLog.setClassname("MoneySourceServiceImpl");
					 wechatLog.setMethodname("wxNotify");
					 wechatLog.setLog(e.getMessage());
					 wechatLog.setLogtime(new Date());
					 wechatLog.setLogdescription("helpInfoService.updateRecord(userUUIDBehelped)");
					 wechatLog.setOther(userUUIDBehelped);
				 }

			 }
			 // 4.更新受助人的User表，因为粉丝增加了。
			 User  beHelpUser=new User();
			 try {
				 beHelpUser=userMapper.selectByUUID(userUUIDBehelped); //这个是受助人user

			 }catch (Exception e){
				 wechatLog.setClassname("MoneySourceServiceImpl");
				 wechatLog.setMethodname("wxNotify");
				 wechatLog.setLog(e.getMessage());
				 wechatLog.setLogtime(new Date());
				 wechatLog.setLogdescription("userMapper.selectByUUID(userUUIDBehelped)");
				 wechatLog.setOther(userUUIDBehelped);
			 }
			 if (beHelpUser!=null){
				 Integer beHelpUserFollow=beHelpUser.getFollowcount(); //收听人的数量
				 Integer acceptMoney=beHelpUser.getAcceptmoney(); //用户获得捐钱的总数
				 Integer amountAccept=beHelpUser.getAmountaccept();

				 beHelpUserFollow=beHelpUserFollow+1;
				 acceptMoney=acceptMoney+moneySource.getMoneynum();
				 amountAccept=amountAccept+1;

				 beHelpUser.setFollowcount(beHelpUserFollow);
				 beHelpUser.setAcceptmoney(acceptMoney);
				 beHelpUser.setAmountaccept(amountAccept);
				 try {
					 int beHelpUserUpdateTag=userMapper.updateByPrimaryKeySelective(beHelpUser); //执行更新
				 }catch (Exception e){
					 wechatLog.setClassname("MoneySourceServiceImpl");
					 wechatLog.setMethodname("wxNotify");
					 wechatLog.setLog(e.getMessage());
					 wechatLog.setLogtime(new Date());
					 wechatLog.setLogdescription("userMapper.updateByPrimaryKeySelective");
					 wechatLog.setOther(userUUIDBehelped);
					 // return;
				 }
				 //5 和 6 涉及到beHelpUser，所在得放在这里面，确保不抛出异常
				 // 5.更新DoubleFans表，受助人成为帮助人的粉丝，
				 Long goodGuyID=donateUser.getUserid();
				 Long beHelpUserID=beHelpUser.getUserid();
				 DoubleFans doubleFans=new DoubleFans();
				 doubleFans.setUseridbefocus(goodGuyID);
				 doubleFans.setUseridfollowe(beHelpUserID);
				 doubleFans.setPaytag((byte) 2);  //不能取消关注
				 Map<String,Object> map= new HashMap<String,Object>();
				 map.put("taUserid",beHelpUserID);
				 map.put("myUserid",goodGuyID);
				 //查询下有没有已经关注，
				 PartWatchPojo partWatchPojo;
				 try {
					 partWatchPojo= doubleFansMapper.watchOrNot(map);
					 if (partWatchPojo==null){
						 //执行插入
						 int  doubleFansTag=doubleFansMapper.insert(doubleFans);
					 }else {
						 //执行更行
						 int updateDoubleFansTag=doubleFansMapper.updateByPrimaryKeySelective(doubleFans);
					 }
				 }catch (Exception e){
					 wechatLog.setClassname("MoneySourceServiceImpl");
					 wechatLog.setMethodname("wxNotify");
					 wechatLog.setLog(e.getMessage());
					 wechatLog.setLogtime(new Date());
					 wechatLog.setLogdescription("doubleFansMapper.watchOrNot");
					 wechatLog.setOther(userUUIDBehelped);
					 return;
				 }

				 // 6.更新Tweet表，捐款人发布一条推文,该表加入userUUID
				 Tweet tweet=new Tweet();
				 String msgContent="感谢@"+donateUser.getUsernickname()+" "+"为@"+beHelpUser.getUsernickname()+"捐钱，互助管家官方对此表示感谢！";
				 tweet.setBackupneight(UUID.randomUUID().toString()); //tweet 的uuid
				 tweet.setUseridtweet(beHelpUser.getUserid()); //发布推文的userid
				 tweet.setSourcemsgid(new Long(1));//1代表非转发
				 tweet.setUseruuidtweet(userGoodGuyUUID);
				 tweet.setMsgcontent(msgContent); //放入推文内容到tweet中
				 tweet.setTagid((byte)1 );
				 tweet.setTopic(new Long(1));
				 tweet.setBoxtimes(0);
				 tweet.setCommenttimes(0);
				 tweet.setOk(0);
				 tweet.setPublishtime(new Date());
				 tweet.setReportedtimes(0);
				 tweet.setPublicsee((byte)1); //1代表可见
				 tweet.setDeletetag((byte)1); //1代表未删除
				 tweet.setTweetbackupsix(0); //默认承诺0，
				 tweet.setBackupnine(donateUser.getUsernickname()); //用户的昵称
				 tweet.setBackupeleven(donateUser.getUserphoto()); //用户的头像地址
				 tweet.setCityname("中国"); //用户所在的城市
				 tweet.setTweetbackupfour(0); //备用4等于1代表是一个普通的推文2代表的是救助
				 try {
					 Integer tag=mainService.addTweet(tweet);
				 }catch (Exception e){
					 wechatLog.setClassname("MoneySourceServiceImpl");
					 wechatLog.setMethodname("wxNotify");
					 wechatLog.setLog(e.getMessage());
					 wechatLog.setLogtime(new Date());
					 wechatLog.setLogdescription("mainService.addTweet");
					 wechatLog.setOther(userGoodGuyUUID);
					 //return;
				 }

			 }

			 // 7.更新HelpInfo表，捐款人信息更新,
			 HelpInfo  helpInfoFgoodGuy=new HelpInfo();
			 try {
				 helpInfoFgoodGuy=helpInfoService.selectRecord(userGoodGuyUUID); //
			 }catch (Exception e){
				 wechatLog.setClassname("MoneySourceServiceImpl");
				 wechatLog.setMethodname("wxNotify");
				 wechatLog.setLog(e.getMessage());
				 wechatLog.setLogtime(new Date());
				 wechatLog.setLogdescription("helpInfoService.selectRecord");
				 wechatLog.setOther(userGoodGuyUUID);
			 }

			 if (helpInfoFgoodGuy!=null){
				 Integer donateMount=0;
				 Integer helpTimes=0;
				 donateMount=moneySource.getMoneynum()+helpInfoFgoodGuy.getDonatemount(); //捐钱总数
				 helpTimes=helpInfoFgoodGuy.getHelptimes()+1; //帮助次数+1
				 helpInfoFgoodGuy.setDonatemount(donateMount);
				 helpInfoFgoodGuy.setHelptimes(helpTimes);
				 //执行更新helpInfo表
				 try {
					 int helpInfoInsertTag=helpInfoService.updateRecord(helpInfoFgoodGuy);
				 }catch (Exception e){
					 wechatLog.setClassname("MoneySourceServiceImpl");
					 wechatLog.setMethodname("wxNotify");
					 wechatLog.setLog(e.getMessage());
					 wechatLog.setLogtime(new Date());
					 wechatLog.setLogdescription("helpInfoService.updateRecord");
					 wechatLog.setOther(userGoodGuyUUID);
				 }

			 }// 按道理是必须存在的，注册的时候就创建了一条记录
			 // 8.更新User表， 捐款人信息更新
			 //User helpUser=new User();
			 //粉丝，捐钱数等增加
			 int  fansCount=donateUser.getFanscount()+1;  //粉丝总数+1
			 int  moneyDonate=donateUser.getUserdonate()+moneySource.getMoneynum(); //捐钱总数
			 int  helpTimes=donateUser.getUserhelpsman()+1;
			 donateUser.setFanscount(fansCount);
			 donateUser.setUserdonate(moneyDonate);
			 donateUser.setUserhelpsman(helpTimes);
			 try {
				 int  helpUserUpdateTag=userMapper.updateByPrimaryKey(donateUser);
			 }catch ( Exception e){
				 wechatLog.setClassname("MoneySourceServiceImpl");
				 wechatLog.setMethodname("wxNotify");
				 wechatLog.setLog(e.getMessage());
				 wechatLog.setLogtime(new Date());
				 wechatLog.setLogdescription("userMapper.updateByPrimaryKey");
				 wechatLog.setOther(userGoodGuyUUID);
			 }
			 return;

		 }else {//没有关联邮箱，执行简单的数据更新  openidUser表和Cash表moneySource表(统一的昵称，头像，userUUID)
			 OpenidUser openidUser=null;
			 String openidUserUUID=UUID.randomUUID().toString();
		 	try{
		 		// 1.对openidUser表进行更新或插入，为了以后关联
				openidUser=openidUserMapper.selectByOpenId(openId);

			}catch (Exception e){
				wechatLog.setClassname("MoneySourceServiceImpl");
				wechatLog.setMethodname("wxNotify");
				wechatLog.setLog(e.getMessage());
				wechatLog.setLogtime(new Date());
				wechatLog.setLogdescription("openidUserMapper.selectByOpenId");
				wechatLog.setOther(openId);
			}
			 if (openidUser==null){ //用户第一次捐钱
				 openidUser=new OpenidUser(); //new
				 openidUser.setOpenid(openId);
				 openidUser.setOpeniduseruuid(openidUserUUID);
				 openidUser.setDonatedate(new Date());
				 openidUser.setUsertotalhelp(1);
				 openidUser.setUsertotaldonate(moneySource.getMoneynum());
				 //插入
				 try{
				 	openidUserMapper.insert(openidUser);
				 }catch (Exception e){
					 wechatLog.setClassname("MoneySourceServiceImpl");
					 wechatLog.setMethodname("wxNotify");
					 wechatLog.setLog(e.getMessage());
					 wechatLog.setLogtime(new Date());
					 wechatLog.setLogdescription("openidUserMapper.insert");
					 wechatLog.setOther(openId);
				 }
			 }else {//用户非首次捐钱
				 int userTotalHelp=openidUser.getUsertotalhelp()+1;
				 int userTotalDonate=openidUser.getUsertotaldonate()+moneySource.getMoneynum();
				 openidUser.setOpenid(openId);
				 openidUser.setOpeniduseruuid(openidUserUUID);
				 openidUser.setDonatedate(new Date());
				 openidUser.setUsertotalhelp(userTotalHelp);
				 openidUser.setUsertotaldonate(userTotalDonate);
				 //更新
				 try {
				 	openidUserMapper.updateByPrimaryKeySelective(openidUser);
				 }catch (Exception e){
					 wechatLog.setClassname("MoneySourceServiceImpl");
					 wechatLog.setMethodname("wxNotify");
					 wechatLog.setLog(e.getMessage());
					 wechatLog.setLogtime(new Date());
					 wechatLog.setLogdescription("openidUserMapper.updateByPrimaryKeySelective");
					 wechatLog.setOther(openId);
				 }

			 }
				// 2.Cash 表更新，为了让用户看到钱数增加
			 Cash  cash=null;
			 try{
				 cash=cashMapper.selectByCashUUID(cashUUID);

			 }catch (Exception e){
				 wechatLog.setClassname("MoneySourceServiceImpl");
				 wechatLog.setMethodname("wxNotify");
				 wechatLog.setLog(e.getMessage());
				 wechatLog.setLogtime(new Date());
				 wechatLog.setLogdescription("cashMapper.selectByCashUUID");
				 wechatLog.setOther(cashUUID);
			 }
			 if (cash!=null){
				 try {
					 Integer  realCash=cash.getRealcash();
					 int helpTimes=cash.getSumbackup()+1;
					 realCash=realCash+moneySource.getMoneynum();
					 cash.setRealcash(realCash);
					 cash.setSumbackup(helpTimes);
					 int cashUpdateTag=cashMapper.updateByPrimaryKeySelective(cash);
				 }catch (Exception e){
					 wechatLog.setClassname("MoneySourceServiceImpl");
					 wechatLog.setMethodname("wxNotify");
					 wechatLog.setLog(e.getMessage());
					 wechatLog.setLogtime(new Date());
					 wechatLog.setLogdescription("cashMapper.updateByPrimaryKeySelective");
					 wechatLog.setOther(cashUUID);
				 }
			 }
			// 3.moneySource 表更新,为了显示在动态列表上
			String  userNickName="互助管家人";
			String  userPhoto="http://onejf30n8.bkt.clouddn.com/gongzhong512.png"; //默认头像地址
			moneySource.setHelpmanusername(userNickName);
			moneySource.setHelpmanphotourl(userPhoto);
			moneySource.setMoneysourceuuid(moneySourceUUID); //32位
			 try {
				 int updateTag=moneySourceMapper.insert(moneySource);  //应该是插入
			 }catch (Exception e){
				 wechatLog.setClassname("MoneySourceServiceImpl");
				 wechatLog.setMethodname("wxNotify");
				 wechatLog.setLog(e.getMessage());
				 wechatLog.setLogtime(new Date());
				 wechatLog.setLogdescription("moneySourceMapper.insert");
				 wechatLog.setOther(moneySourceUUID);
			 }
		 }
	}


	//获取"支持了"列表
	public  List<MoneySource> getGuyHelpMe(Map<String,Object>  map){
     	List<MoneySource>  lms=moneySourceMapper.getMoneySourceList(map);
		return  lms;
	}

	//获取完整的，"支持了" 列表
	public  List<MoneySource> getGuyHelpMeByCashUUID(Map<String,Object>  map){
		List<MoneySource>  lms=moneySourceMapper.getMoneySourceListByCashUUID(map);
		return  lms;
	} //获取帮助我的人

	//对"支持了" 进行评论
	public  CommonRsp addOneComment(MoneysrcPinglun  moneysrcPinglun){
		   CommonRsp  commonRsp=new CommonRsp();
		   int returnTag=moneysrcPinglunMapper.insertSelective(moneysrcPinglun); //
		   if (returnTag==1){
			   commonRsp.setMsg("插入成功");
			   commonRsp.setRetcode(2000);
		   }else{
		   		commonRsp.setMsg("插入失败");
		   		commonRsp.setRetcode(2001);
		   }
		   return  commonRsp;
	}

   //删除了"支持了"的评论
   public  CommonRsp deleteOneComment(Long  moneySrcPingluniD ){
		CommonRsp  commonRsp=new CommonRsp();
        int deleteTag= moneysrcPinglunMapper.deleteByPrimaryKey(moneySrcPingluniD);
        if (deleteTag==1){
        	commonRsp.setMsg("删除成功");
        	commonRsp.setRetcode(2000);
		}else {
        	commonRsp.setMsg("删除失败");
        	commonRsp.setRetcode(2001);
		}
	   return  commonRsp;
   }

	//根据MoneySourceID 获取其对应的评论
	public  List<MoneysrcPinglun> getMoneySourcePingLuns(Map<String,Object>  map){
		List<MoneysrcPinglun> lmp=moneysrcPinglunMapper.getMoneySourcePingLunList(map);
		return  lmp;
	}

}
