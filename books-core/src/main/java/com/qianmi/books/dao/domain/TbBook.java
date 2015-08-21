package com.qianmi.books.dao.domain;

import java.math.BigDecimal;
import java.util.Date;

public class TbBook {
    /** 书编号 */
    private String bookId;
    /** 书名称 */
    private String bookName;
    /** 书持有者,可以是网点也可以是用户 */
    private String sellerId;
    /** 书所有者 */
    private String owner;
    /** 书的状态 0:停止出借 1:可出借 2:已预约 3:已借出 */
    private Integer state;
    /** 添加时间 */
    private Date addTime;
    /** 书的页数 */
    private Integer pageSize;
    /** 作者 */
    private String author;
    /** 介绍 */
    private String introduce;
    /** 图片地址 */
    private String pictureUrl;
    /** 借阅次数 */
    private Integer borrowCount;
    /** 借阅价格 */
    private BigDecimal borrowCash;
    /** 是否支持还到上级网点0:支持，1:不支持 */
    private Integer backPoint;
    /** 借阅押金 */
    private BigDecimal borrowDeposit;

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

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public Integer getBorrowCount() {
        return borrowCount;
    }

    public void setBorrowCount(Integer borrowCount) {
        this.borrowCount = borrowCount;
    }

    public BigDecimal getBorrowCash() {
        return borrowCash;
    }

    public void setBorrowCash(BigDecimal borrowCash) {
        this.borrowCash = borrowCash;
    }

    public Integer getBackPoint() {
        return backPoint;
    }

    public void setBackPoint(Integer backPoint) {
        this.backPoint = backPoint;
    }

    public BigDecimal getBorrowDeposit() {
        return borrowDeposit;
    }

    public void setBorrowDeposit(BigDecimal borrowDeposit) {
        this.borrowDeposit = borrowDeposit;
    }

}