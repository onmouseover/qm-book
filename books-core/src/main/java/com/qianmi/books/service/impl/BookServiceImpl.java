package com.qianmi.books.service.impl;

import com.qianmi.books.dao.domain.TbBook;
import com.qianmi.books.dao.domain.TbUser;
import com.qianmi.books.exception.CheckedException;
import com.qianmi.books.service.BookService;

import java.util.List;

/**
 * Created by caowei on 15-8-21.
 */
public class BookServiceImpl implements BookService {
    @Override
    public String apply(String borrowUserId, String sellerUserId, String bookId) throws CheckedException {
        return null;
    }

    @Override
    public void lend(String borrowUserId, String sellerUserId, String applyId) throws CheckedException {

    }

    @Override
    public void lend(String borrowUserId, String sellerUserId, String bookId, String borrowUserKey) throws CheckedException {

    }

    @Override
    public void addBook(TbBook tbBook) throws CheckedException {

    }

    @Override
    public void modifyBook(TbBook tbBook) throws CheckedException {

    }

    @Override
    public void modifyBookState(String bookId, int state) throws CheckedException {

    }

    @Override
    public void deleteBook(String bookId) throws CheckedException {

    }

    @Override
    public List<TbBook> queryBookList(TbBook tbBook) throws CheckedException {
        return null;
    }

    @Override
    public TbBook queryBookDetail(String bookId) throws CheckedException {
        return null;
    }

    @Override
    public void confirmBookBack(String sellerUserId, String borrowId) throws CheckedException {

    }

    @Override
    public TbUser register(TbUser tbUser) throws CheckedException {
        return null;
    }

    @Override
    public TbUser login(String userId) throws CheckedException {
        return null;
    }

    @Override
    public TbUser getUser(String userId) throws CheckedException {
        return null;
    }
}
