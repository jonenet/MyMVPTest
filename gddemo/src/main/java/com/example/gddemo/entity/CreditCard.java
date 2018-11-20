package com.example.gddemo.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 作者:      周来
 * 包名:      com.example.gddemo.entity
 * 工程名:    MyMVPTest
 * 时间:      2018/11/19
 * 说明:
 */
@Entity
public class CreditCard {

    @Id
    private Long id;
    private Long userId;
    private String userName;//持有者名字
    private String cardNum;//卡号
    private String whichBank;//哪个银行的
    private int cardType;//卡等级，分类 0 ~ 5

    @Generated(hash = 637432226)
    public CreditCard(Long id, Long userId, String userName, String cardNum,
            String whichBank, int cardType) {
        this.id = id;
        this.userId = userId;
        this.userName = userName;
        this.cardNum = cardNum;
        this.whichBank = whichBank;
        this.cardType = cardType;
    }

    @Generated(hash = 1860989810)
    public CreditCard() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public String getWhichBank() {
        return whichBank;
    }

    public void setWhichBank(String whichBank) {
        this.whichBank = whichBank;
    }

    public int getCardType() {
        return cardType;
    }

    public void setCardType(int cardType) {
        this.cardType = cardType;
    }
}
