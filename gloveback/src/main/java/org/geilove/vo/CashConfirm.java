package org.geilove.vo;

import org.geilove.pojo.Cash;
import org.geilove.pojo.Confirm;

import java.util.List;

/**
 * Created by mfhj-dz-001-424 on 17/2/23.
 */
public class CashConfirm {

    private Cash  cash;
    private List<Confirm> confirmList;

    public Cash getCash() {
        return cash;
    }

    public void setCash(Cash cash) {
        this.cash = cash;
    }

    public List<Confirm> getConfirmList() {
        return confirmList;
    }

    public void setConfirmList(List<Confirm> confirmList) {
        this.confirmList = confirmList;
    }
}
