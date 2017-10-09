package org.geilove.service;

import org.geilove.pojo.Cash;

public interface CashService {

     public  Cash  getCashRecord(Long cashid);

     public  Cash  getCashRecordByUUID(String  cashuuid); //根据cash的uuid获取cash

     public int cashInsert(Cash cash);
}
