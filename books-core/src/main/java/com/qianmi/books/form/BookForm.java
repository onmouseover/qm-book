package com.qianmi.books.form;

import com.qianmi.books.dao.domain.TbBook;
import org.hibernate.validator.constraints.NotBlank;

import java.math.BigDecimal;

/**
 * Created with of666.
 * User: of666
 * Date: 2015/8/21 0021
 * Time: 17:41
 */
public class BookForm extends TbBook {
    /** 书名称 */
    private String bookName;
    /** 书持有者,可以是网点也可以是用户 */
    private String sellerId;
    /** 书所有者 */
    private String owner;
    /** 书的页数 */
    private Integer pageSize;
    /** 作者 */
    private String author;
    /** 介绍 */
    private String introduce;
    /** 图片地址 */
    private String pictureUrl;
    /** 借阅价格 */
    private BigDecimal borrowCash;
    /** 是否支持还到上级网点0:支持，1:不支持 */
    private Integer backPoint;
    /** 借阅押金 */
    private BigDecimal borrowDeposit;

    private Integer bookType;

    public Integer getBookType() {
        return bookType;
    }

    public void setBookType(Integer bookType) {
        this.bookType = bookType;
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
