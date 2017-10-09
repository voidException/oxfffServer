package org.geilove.service;

import org.geilove.pojo.HelpInfo;

/**
 * Created by aihaitao on 14/5/2017.
 */
public interface HelpInfoService {

    public  HelpInfo selectRecord(String  useruuid);
    public  int  insertRecord(HelpInfo helpInfo);
    public  int  updateRecord(HelpInfo helpInfo);
}
