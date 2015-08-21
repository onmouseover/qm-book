package com.qianmi.books.dao.domain;

import java.math.BigDecimal;
import java.util.Date;

public class TbCreditRecord {
    private String recordId;
    private String userId;
    private Date recordTime;
    /**
     * 资金类型 1:押金支出 2:看书支出 3:过期扣除 4:提现 11:押金退还 12:借书收入 13:过期收入  14:充值
     */
    private Integer type;
    private String borrowId;

    private BigDecimal inCash;
    private BigDecimal outCash;

    public BigDecimal getOutCash() {
        return outCash;
    }

    public void setOutCash(BigDecimal outCash) {
        this.outCash = outCash;
    }

    public BigDecimal getInCash() {

        return inCash;
    }

    public void setInCash(BigDecimal inCash) {
        this.inCash = inCash;
    }

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Date recordTime) {
        this.recordTime = recordTime;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getBorrowId() {
        return borrowId;
    }

    public void setBorrowId(String borrowId) {
        this.borrowId = borrowId;
    }

}