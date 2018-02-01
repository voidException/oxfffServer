package org.geilove.service.impl;

import net.sf.ehcache.search.aggregator.Sum;
import org.geilove.dao.CompanyputaoMapper;
import org.geilove.dao.PayMoneyMapper;
import org.geilove.dao.PublicMapper;
import org.geilove.dao.UserAccountMapper;
import org.geilove.pojo.Companyputao;
import org.geilove.pojo.PayMoney;
import org.geilove.pojo.Public;
import org.geilove.pojo.UserAccount;
import org.geilove.service.SumInfoService;
import org.geilove.util.Arith;
import org.geilove.vo.SumInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by aihaitao on 30/1/2018.
 */
@Service("SumInfoService")
public class SumInfoServiceImpl implements SumInfoService {
    @Resource
    private UserAccountMapper userAccountMapper;
    @Resource
    private PublicMapper  publicMapper;
    @Resource
    private PayMoneyMapper payMoneyMapper;
    @Resource
    private CompanyputaoMapper companyputaoMapper;

    public SumInfo getSumInfo(String  helpType){
        SumInfo  sumInfo=new SumInfo();
        sumInfo.setHelpType(helpType);

        if ("staff".equals(helpType) || "employee".equals(helpType)){
            ///  余额  人数  平均- 适用于企业companyputao表
            List<Companyputao> companyputaoList;
            try{
                companyputaoList=companyputaoMapper.getSumInfo(helpType);
                if (companyputaoList==null || companyputaoList.isEmpty()){
                    sumInfo.setAverage("0");
                    sumInfo.setSumMan("0");
                    sumInfo.setSumMoneyRemain("0");
                }
                else { //
                    Integer sumRen=companyputaoList.size();
                    Double sumMoneyRemain=0.0; //当前余额
                    for (Companyputao companyputao:companyputaoList){
                        String totalMoney=companyputao.getTotalmoenystr();
                        sumMoneyRemain=Arith.add(sumMoneyRemain,Double.valueOf(totalMoney));
                    }
                    Double avaMoney=Arith.div(sumMoneyRemain,Double.valueOf(sumRen),3);
                    sumInfo.setSumMoneyRemain(String.valueOf(sumMoneyRemain)); //当前余额
                    sumInfo.setSumMan(String.valueOf(sumRen));  //参与该互助计划的人数
                    sumInfo.setAverage(String.valueOf(avaMoney));  //人均钱数
                    sumInfo.setSumMoney("150"); //最低互助金额

                }
            }catch (Exception e){
                throw e;
            }

        }else {
            sumInfo.setSumMoney("0");
            //  余额  人数  平均--适用于个人
            List<UserAccount> userAccounts;
            try {
                userAccounts=userAccountMapper.getSumInfo(helpType);
                if (userAccounts==null ||userAccounts.isEmpty()){
                    sumInfo.setAverage("0");
                    sumInfo.setSumMan("0");
                    sumInfo.setSumMoneyRemain("0");
                }else {
                    // 根据获取的userAccounts，获取参与该计划的总人数，计算出总金额，最后计算出平均数，BigDecimal计算
                    Integer sumRen=userAccounts.size(); //参与该计划的人数。一定不是0
                    Double sumMoneyRemain=0.0; //当前余额
                    for (UserAccount userAccount:userAccounts){
                        String payTotalMoney=userAccount.getPaytotalmoney(); //该账户的金额
                        sumMoneyRemain= Arith.add(sumMoneyRemain,Double.valueOf(payTotalMoney));
                    }
                    //
                    Double avaMoney=Arith.div(sumMoneyRemain,Double.valueOf(sumRen),3);
                    sumInfo.setSumMoneyRemain(String.valueOf(sumMoneyRemain)); //当前余额
                    sumInfo.setSumMan(String.valueOf(sumRen));  //参与该互助计划的人数
                    sumInfo.setAverage(String.valueOf(avaMoney));  //人均钱数
                }

            }catch (Exception e){
                throw e;
            }

        }//else

        //互助次数  从Public 表中计算
        List<Public> publicList;
        try{
            Map<String,Object> map=new HashMap<>();
            map.put("helpType",helpType);
            publicList=publicMapper.getNumByhelpType(map);
            if (publicList==null || publicList.isEmpty()){
                sumInfo.setHelpTimes("0");
            }else{
                sumInfo.setHelpTimes(String.valueOf( publicList.size()));
            }
        }catch (Exception e){
            throw  e;
        }

        return  sumInfo;
    }
}