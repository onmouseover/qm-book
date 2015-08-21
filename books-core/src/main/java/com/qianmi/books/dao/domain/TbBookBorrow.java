package com.qianmi.books.dao.domain;

import java.math.BigDecimal;
import java.util.Date;

public class TbBookBorrow {
    private String borrowId;
    private String bookId;
    private String bookName;
    private Date startTime;
    private Date endTime;
    private String borrowUserId;
    private Integer state;
    private String sellerUserId;
    private String pointUserId;
    private Integer backPoint;
    private BigDecimal borrowCash;
    private BigDecimal borrowDeposit;

    public String getBorrowId() {
        return borrowId;
    }

    public void setBorrowId(String borrowId) {
        this.borrowId = borrowId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getBorrowUserId() {
        return borrowUserId;
    }

    public void setBorrowUserId(String borrowUserId) {
        this.borrowUserId = borrowUserId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getSellerUserId() {
        return sellerUserId;
    }

    public void setSellerUserId(String sellerUserId) {
        this.sellerUserId = sellerUserId;
    }

    public String getPointUserId() {
        return pointUserId;
    }

    public void setPointUserId(String pointUserId) {
        this.pointUserId = pointUserId;
    }

    public Integer getBackPoint() {
        return backPoint;
    }

    public void setBackPoint(Integer backPoint) {
        this.backPoint = backPoint;
    }

    public BigDecimal getBorrowCash() {
        return borrowCash;
    }

    public void setBorrowCash(BigDecimal borrowCash) {
        this.borrowCash = borrowCash;
    }

    public BigDecimal getBorrowDeposit() {
        return borrowDeposit;
    }

    public void setBorrowDeposit(BigDecimal borrowDeposit) {
        this.borrowDeposit = borrowDeposit;
    }

}