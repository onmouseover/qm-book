package com.qianmi.books.dao.domain;

import java.math.BigDecimal;
import java.util.Date;

public class TbUser {
    private String userId;
    private String userName;
    private String password;
    private Integer type;
    private String pointUserId;
    private Date regTime;
    private BigDecimal blance;
    private Integer borrowCount;
    private String borrowKey;
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBorrowKey() {
        return borrowKey;
    }

    public void setBorrowKey(String borrowKey) {
        this.borrowKey = borrowKey;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getPointUserId() {
        return pointUserId;
    }

    public void setPointUserId(String pointUserId) {
        this.pointUserId = pointUserId;
    }

    public Date getRegTime() {
        return regTime;
    }

    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }

    public BigDecimal getBlance() {
        return blance;
    }

    public void setBlance(BigDecimal blance) {
        this.blance = blance;
    }

    public Integer getBorrowCount() {
        return borrowCount;
    }

    public void setBorrowCount(Integer borrowCount) {
        this.borrowCount = borrowCount;
    }

}