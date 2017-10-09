package org.geilove.response;

import org.geilove.pojo.Statistics;

/**
 * Created by aihaitao on 8/7/2017.
 */
public class StatisticsRsp {

    private Integer retcode ;
    private String msg;
    private Statistics  statistics;

    public Integer getRetcode() {
        return retcode;
    }
    public void setRetcode(Integer retcode) {
        this.retcode = retcode;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public Statistics getStatistics() {
        return statistics;
    }

    public void setStatistics(Statistics statistics) {
        this.statistics = statistics;
    }
}
