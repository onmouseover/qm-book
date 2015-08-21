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
import com.qianmi.books.util.uud.UUID;
import com.qianmi.books.util.uud.UUIDGener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
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

        TbUser tbUser = this.tbUserDao.getTbUser(borrowUserId);

        TbBook tbBookUpdate = new TbBook();
        tbBookUpdate.setBookId(tbBook.getBookId());
        tbBookUpdate.setState(Contents.BookState.LENDED);
        tbBookUpdate.setApplyUserId("");
        tbBookUpdate.setApplyId("");
        int row = tbBookDao.updateTbBook(tbBookUpdate);
        if (row == 0) {
            throw new CheckedException("更新为已借出失败");
        }

        TbBookBorrow tbBookBorrow = new TbBookBorrow();
        tbBookBorrow.setBorrowId(UUIDGener.getUUID());
        tbBookBorrow.setBookId(tbBook.getBookId());
        tbBookBorrow.setBackPoint(tbBook.getBackPoint());
        tbBookBorrow.setBookName(tbBook.getBookName());
        tbBookBorrow.setBorrowCash(tbBook.getBorrowCash());
        tbBookBorrow.setBorrowDeposit(tbBook.getBorrowDeposit());
        tbBookBorrow.setBorrowUserId(borrowUserId);
        tbBookBorrow.setPointUserId(tbUser.getPointUserId());
        tbBookBorrow.setStartTime(new Date());
        tbBookBorrow.setState(Contents.BookBorrowState.UNBACK);
        this.tbBookBorrowDao.insertTbBookBorrow(tbBookBorrow);

    }

    @Override
    public void lend(String borrowUserId, String sellerUserId, String bookId, String borrowUserKey) throws CheckedException {
        TbBook tbBook = this.tbBookDao.getTbBook(bookId);
        if (Contents.BookState.CAN_BORROW != tbBook.getState()) {
            throw new CheckedException("当前书不可预约");
        }

        TbUser tbUser = this.tbUserDao.getTbUser(borrowUserId);

        TbBook tbBookUpdate = new TbBook();
        tbBookUpdate.setBookId(tbBook.getBookId());
        tbBookUpdate.setState(Contents.BookState.LENDED);
        int row = tbBookDao.updateTbBook(tbBookUpdate);
        if (row == 0) {
            throw new CheckedException("更新为已借出失败");
        }

        TbBookBorrow tbBookBorrow = new TbBookBorrow();
        tbBookBorrow.setBorrowId(UUIDGener.getUUID());
        tbBookBorrow.setBookId(tbBook.getBookId());
        tbBookBorrow.setBackPoint(tbBook.getBackPoint());
        tbBookBorrow.setBookName(tbBook.getBookName());
        tbBookBorrow.setBorrowCash(tbBook.getBorrowCash());
        tbBookBorrow.setBorrowDeposit(tbBook.getBorrowDeposit());
        tbBookBorrow.setBorrowUserId(borrowUserId);
        tbBookBorrow.setPointUserId(tbUser.getPointUserId());
        tbBookBorrow.setStartTime(new Date());
        tbBookBorrow.setState(Contents.BookBorrowState.UNBACK);
        this.tbBookBorrowDao.insertTbBookBorrow(tbBookBorrow);
    }

    @Override
    public void addBook(TbBook tbBook) throws CheckedException {
        tbBook.setAddTime(new Date());
        tbBook.setBookId(UUIDGener.getUUID());
        tbBook.setState(Contents.BookState.CAN_BORROW);
        this.tbBookDao.insertTbBook(tbBook);
    }

    @Override
    public void modifyBook(TbBook tbBook) throws CheckedException {
        int row = this.tbBookDao.updateTbBook(tbBook);
        if (row == 0) {
            throw new CheckedException("修改书籍失败");
        }
    }

    @Override
    public void modifyBookState(String bookId, int state) throws CheckedException {
        TbBook tbBook = new TbBook();
        tbBook.setBookId(bookId);
        tbBook.setState(state);
        int row = this.tbBookDao.updateTbBook(tbBook);
        if (row == 0) {
            throw new CheckedException("修改状态失败");
        }
    }

    @Override
    public void deleteBook(String bookId) throws CheckedException {
        int row = this.tbBookDao.deleteTbBook(bookId);
        if (row == 0) {
            throw new CheckedException("删除书籍失败");
        }
    }

    @Override
    public List<TbBook> queryBookList(TbBook tbBook) {
        List<TbBook> list = this.tbBookDao.getTbBookList(tbBook);
        return list;
    }

    @Override
    public TbBook queryBookDetail(String bookId) {
        TbBook tbBook = this.tbBookDao.getTbBook(bookId);
        return tbBook;
    }

    @Override
    public void confirmBookBack(String sellerUserId, String borrowId) throws CheckedException {
        TbBookBorrow tbBookBorrow = this.tbBookBorrowDao.getTbBookBorrow(borrowId);
        if (Contents.BookBorrowState.UNBACK != tbBookBorrow.getState()) {
            throw new CheckedException("当前书不是为归还状态");
        }

        TbBookBorrow tbBookBorrowUpdate = new TbBookBorrow();
        tbBookBorrowUpdate.setBorrowId(borrowId);
        tbBookBorrowUpdate.setState(Contents.BookBorrowState.BACK);
        tbBookBorrowUpdate.setEndTime(new Date());
        int row = this.tbBookBorrowDao.updateTbBookBorrow(tbBookBorrowUpdate);
        if (row == 0) {
            throw new CheckedException("确认收到还书失败");
        }
        TbBook tbBookUpdate = new TbBook();
        tbBookUpdate.setBookId(tbBookBorrow.getBookId());
        tbBookUpdate.setState(Contents.BookState.CAN_BORROW);
        this.tbBookDao.updateTbBook(tbBookUpdate);
    }

    @Override
    public TbUser register(TbUser tbUser) throws CheckedException {

        if (this.getUserByName(tbUser.getUserName()) != null) {
            throw new CheckedException("已经存在：" + tbUser.getUserName() + " 的用户");

        }
        tbUser.setUserId(UUIDGener.getUUID());
        tbUser.setRegTime(new Date());
        this.tbUserDao.insertTbUser(tbUser);
        return tbUserDao.getTbUser(tbUser.getUserId());
    }


    @Override
    public TbUser getUser(String userId) {
        TbUser tbUser = this.tbUserDao.getTbUser(userId);
        return tbUser;
    }


    @Override
    public TbUser getUserByName(String userName) {
        TbUser tbUserQuery = new TbUser();
        tbUserQuery.setUserName(userName);
        List<TbUser> tbUserList = this.tbUserDao.getTbUserList(tbUserQuery);
        if (tbUserList.size() == 0) {
            return null;

        } else {
            return tbUserList.get(0);
        }
    }

    @Override
    public void withdraw(String userId, BigDecimal cash) throws CheckedException {
        TbUser tbUser = this.tbUserDao.getTbUser(userId);
        BigDecimal curBlanch = tbUser.getBlance();
        if (curBlanch.compareTo(cash) < 0) {
            throw new CheckedException("当前余额:" + curBlanch.toString() + "不足提现");
        }

        TbUser tbUserUpdate = new TbUser();
        tbUserUpdate.setUserId(userId);
        tbUserUpdate.setBlance(curBlanch.subtract(cash));
        int row = this.tbUserDao.updateTbUser(tbUserUpdate);
        if (row == 0) {
            throw new CheckedException("修改资金余额失败");
        }

        TbCreditRecord tbCreditRecord = new TbCreditRecord();
        tbCreditRecord.setRecordId(UUIDGener.getUUID());
        tbCreditRecord.setRecordTime(new Date());
        tbCreditRecord.setType(Contents.CreditType.WITHDRAW);
        tbCreditRecord.setUserId(userId);
        this.tbCreditRecordDao.insertTbCreditRecord(tbCreditRecord);
    }

    @Override
    public void deposit(String userId, BigDecimal cash) throws CheckedException {
        TbUser tbUser = this.tbUserDao.getTbUser(userId);
        BigDecimal curBlanch = tbUser.getBlance();

        TbUser tbUserUpdate = new TbUser();
        tbUserUpdate.setUserId(userId);
        tbUserUpdate.setBlance(curBlanch.add(cash));
        int row = this.tbUserDao.updateTbUser(tbUserUpdate);
        if (row == 0) {
            throw new CheckedException("修改资金余额失败");
        }

        TbCreditRecord tbCreditRecord = new TbCreditRecord();
        tbCreditRecord.setRecordId(UUIDGener.getUUID());
        tbCreditRecord.setRecordTime(new Date());
        tbCreditRecord.setType(Contents.CreditType.DEPOSIT);
        tbCreditRecord.setUserId(userId);
        this.tbCreditRecordDao.insertTbCreditRecord(tbCreditRecord);
    }

    @Override
    public TbCreditRecord getTbCreditRecord(String recordId) {
        return this.tbCreditRecordDao.getTbCreditRecord(recordId);
    }

    @Override
    public List<TbCreditRecord> getTbCreditRecordList(TbCreditRecord tbCreditRecord) {
        return this.tbCreditRecordDao.getTbCreditRecordList(tbCreditRecord);
    }
}
