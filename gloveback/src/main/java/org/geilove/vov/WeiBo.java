package org.geilove.vov;

import org.geilove.pojo.Tweet;

/**
 * Created by mfhj-dz-001-424 on 17/2/28.
 */
public class WeiBo {

    private Tweet tuiwen;

    private Tweet zhuanfaTuiwen; //被转发的推文

    public Tweet getTuiwen() {
        return tuiwen;
    }

    public void setTuiwen(Tweet tuiwen) {
        this.tuiwen = tuiwen;
    }

    public Tweet getZhuanfaTuiwen() {
        return zhuanfaTuiwen;
    }

    public void setZhuanfaTuiwen(Tweet zhuanfaTuiwen) {
        this.zhuanfaTuiwen = zhuanfaTuiwen;
    }
}
