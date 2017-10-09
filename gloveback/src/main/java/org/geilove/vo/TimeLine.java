package org.geilove.vo;

import org.geilove.pojo.Cash;
import org.geilove.pojo.Confirm;
import org.geilove.pojo.Tweet;
import org.geilove.pojo.User;

import java.util.List;

/**
 * Created by mfhj-dz-001-424 on 17/2/14.
 */
public class TimeLine {
    private  Tweet tweet;
    private  Cash  cash;
    private  List<Confirm> confirmList;
    private  UserPartProfile userPartProfile;

    public void setUserPartProfile(UserPartProfile userPartProfile) {
        this.userPartProfile = userPartProfile;
    }

    public Tweet getTweet() {
        return tweet;
    }

    public void setTweet(Tweet tweet) {
        this.tweet = tweet;
    }

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


    public UserPartProfile getUserPartProfile() {
        return userPartProfile;
    }
}
