package com.qianmi.books.service;

import com.qianmi.books.dao.domain.TbBook;
import com.qianmi.books.dao.domain.TbUser;
import com.qianmi.books.exception.CheckedException;

import java.util.List;

/**
 * Created by caowei on 15-8-21.
 */
public interface BookService {

    /**
     * 预订图书，返回预定码
     *
     * @param borrowUserId
     * @param sellerUserId
     * @param bookId
     * @return
     * @throws CheckedException
     */
    String apply(String borrowUserId, String sellerUserId, String bookId) throws CheckedException;


    /**
     * 通过预定码借出图书
     *
     * @param borrowUserId
     * @param sellerUserId
     * @param applyId
     * @throws CheckedException
     */
    void lend(String borrowUserId, String sellerUserId, String applyId) throws CheckedException;

    /**
     * 根据买家借书码借出图书
     *
     * @param borrowUserId
     * @param sellerUserId
     * @param bookId
     * @param borrowUserKey
     * @throws CheckedException
     */
    void lend(String borrowUserId, String sellerUserId, String bookId, String borrowUserKey) throws CheckedException;

    /**
     * 添加图书
     *
     * @param tbBook
     * @throws CheckedException
     */
    void addBook(TbBook tbBook) throws CheckedException;

    /**
     * 修改图书
     *
     * @param tbBook
     * @throws CheckedException
     */
    void modifyBook(TbBook tbBook) throws CheckedException;

    /**
     * 修改图书上架状态 0:下架  1:上架
     *
     * @param bookId
     * @param state
     * @throws CheckedException
     */
    void modifyBookState(String bookId, int state) throws CheckedException;

    /**
     * 删除图书
     *
     * @param bookId
     * @throws CheckedException
     */
    void deleteBook(String bookId) throws CheckedException;

    /**
     * 查询图书列表
     *
     * @param tbBook
     * @return
     * @throws CheckedException
     */
    List<TbBook> queryBookList(TbBook tbBook) throws CheckedException;

    /**
     * 查询图书详情
     *
     * @param bookId
     * @return
     * @throws CheckedException
     */
    TbBook queryBookDetail(String bookId) throws CheckedException;

    /**
     * 确定图书已经归还
     *
     * @param sellerUserId
     * @param borrowId
     * @throws CheckedException
     */
    void confirmBookBack(String sellerUserId, String borrowId) throws CheckedException;

    /**
     * 用户注册
     *
     * @param tbUser
     * @return
     * @throws CheckedException
     */
    TbUser register(TbUser tbUser) throws CheckedException;
    /**
     * 用户登录
     *
     * @param userId
     * @return
     * @throws CheckedException
     */
    TbUser login(String userId) throws CheckedException;

    /**
     * 获取用户
     *
     * @param userId
     * @return
     * @throws CheckedException
     */
    TbUser getUser(String userId) throws CheckedException;


}
