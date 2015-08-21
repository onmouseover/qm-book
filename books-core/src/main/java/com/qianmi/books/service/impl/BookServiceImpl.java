package com.qianmi.books.service.impl;

import com.qianmi.books.dao.TbBookBorrowDao;
import com.qianmi.books.dao.TbBookDao;
import com.qianmi.books.dao.TbCreditRecordDao;
import com.qianmi.books.dao.TbUserDao;
import com.qianmi.books.dao.domain.TbBook;
import com.qianmi.books.dao.domain.TbBookBorrow;
import com.qianmi.books.dao.domain.TbCreditRecord;
import com.qianmi.books.dao.domain.TbUser;
import com.qianmi.books.domain.Contents;
import com.qianmi.books.exception.CheckedException;
import com.qianmi.books.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by caowei on 15-8-21.
 */
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private TbBookDao tbBookDao;
    @Autowired
    private TbBookBorrowDao tbBookBorrowDao;
    @Autowired
    private TbUserDao tbUserDao;
    @Autowired
    private TbCreditRecordDao tbCreditRecordDao;

    @Override
    public String apply(String borrowUserId, String sellerUserId, String bookId) throws CheckedException {
        TbBook tbBook = this.tbBookDao.getTbBook(bookId);
        if (Contents.BookState.CAN_BORROW != tbBook.getState()) {
            throw new CheckedException("当前书不可预约");
        }

        double random = Math.random();
        if (random < 0.1) {
            random = random + 0.1;
        }
        int applyId = (int)(random * 10000);
        TbBook tbBookUpdate = new TbBook();
        tbBookUpdate.setBookId(tbBook.getBookId());
        tbBookUpdate.setApplyId(String.valueOf(applyId));
        tbBookUpdate.setApplyUserId(borrowUserId);
        int row = this.tbBookDao.updateTbBook(tbBookUpdate);
        if (row == 0) {
            throw new CheckedException("当前书不可预约");
        }
        return tbBookUpdate.getApplyId();
    }

    @Override
    public void lend(String borrowUserId, String sellerUserId, String applyId) throws CheckedException {
        TbBook tbBookCondition = new TbBook();
        tbBookCondition.setApplyId(applyId);
        tbBookCondition.setApplyUserId(borrowUserId);
        tbBookCondition.setSellerId(sellerUserId);
        List<TbBook> tbBookList = this.tbBookDao.getTbBookList(tbBookCondition);
        if (tbBookList.size() == 0) {
            throw new CheckedException("此预约码已过期或不存在");
        }

        TbBook tbBook = tbBookList.get(0);
        TbBookBorrow tbBookBorrow = new TbBookBorrow();
        tbBookBorrow.setBookId(tbBook.getBookId());
        tbBookBorrow.setBackPoint(tbBook.getBackPoint());
        tbBookBorrow.setBookName(tbBook.getBookName());
        tbBookBorrow.setBorrowCash(tbBook.getBorrowCash());
        tbBookBorrow.setBorrowDeposit(tbBook.getBorrowDeposit());
        tbBookBorrow.setBorrowUserId(borrowUserId);
        this.tbBookBorrowDao.insertTbBookBorrow(tbBookBorrow);
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
        List<TbBook> list = new ArrayList<TbBook>();
        list.add(new TbBook());
        return list;
    }

    @Override
    public TbBook queryBookDetail(String bookId) throws CheckedException {
        return new TbBook();

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
