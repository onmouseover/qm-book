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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
        tbBookUpdate.setState(Contents.BookState.APPLY);
        int row = this.tbBookDao.updateTbBook(tbBookUpdate);
        if (row == 0) {
            throw new CheckedException("当前书不可预约");
        }
        return tbBookUpdate.getApplyId();
    }

    @Override
    public void lend(String sellerUserId, String applyId) throws CheckedException {
        TbBook tbBookCondition = new TbBook();
        tbBookCondition.setApplyId(applyId);
        tbBookCondition.setSellerId(sellerUserId);
        List<TbBook> tbBookList = this.tbBookDao.getTbBookList(tbBookCondition);
        if (tbBookList.size() == 0) {
            throw new CheckedException("此预约码已过期或不存在");
        }
        TbBook tbBook = tbBookList.get(0);
        String borrowUserId = tbBook.getApplyUserId();
        TbUser tbUser = this.tbUserDao.getTbUser(borrowUserId);
        TbUser sellerUser = this.tbUserDao.getTbUser(sellerUserId);

        TbBook tbBookUpdate = new TbBook();
        tbBookUpdate.setBookId(tbBook.getBookId());
        tbBookUpdate.setState(Contents.BookState.LENDED);
        tbBookUpdate.setApplyUserId("");
        tbBookUpdate.setApplyId("");
        tbBookUpdate.setBorrowCount(tbBook.getBorrowCount() + 1); // 借出次数加1
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

        // 扣钱
        TbUser tbUserUpdate = new TbUser();
        tbUserUpdate.setUserId(borrowUserId);
        tbUserUpdate.setBlance(tbUser.getBlance().subtract(tbBook.getBorrowCash()).subtract(tbBook.getBorrowDeposit()));
        tbUserUpdate.setBorrowCount(tbUser.getBorrowCount() + 1); // 借书次数加1
        this.tbUserDao.updateTbUser(tbUserUpdate);

        // 加钱
        TbUser tbSellerUserUpdate = new TbUser();
        tbSellerUserUpdate.setUserId(sellerUserId);
        tbSellerUserUpdate.setBlance(sellerUser.getBlance().add(tbBook.getBorrowCash()));
        this.tbUserDao.updateTbUser(tbSellerUserUpdate);

        // 增加资金记录
        // 看书费
        TbCreditRecord borrowRecord = new TbCreditRecord();
        borrowRecord.setRecordId(UUIDGener.getUUID());
        borrowRecord.setBorrowId(tbBookBorrow.getBorrowId());
        borrowRecord.setRecordTime(new Date());
        borrowRecord.setType(Contents.CreditType.LOOK_OUT);
        borrowRecord.setUserId(borrowUserId);
        borrowRecord.setOutCash(tbBook.getBorrowCash());
        this.tbCreditRecordDao.insertTbCreditRecord(borrowRecord);

        // 押金费
        borrowRecord.setRecordId(UUIDGener.getUUID());
        borrowRecord.setType(Contents.CreditType.YAJIN_OUT);
        borrowRecord.setUserId(borrowUserId);
        borrowRecord.setOutCash(tbBook.getBorrowDeposit());
        this.tbCreditRecordDao.insertTbCreditRecord(borrowRecord);

        // 增加资金记录
        // 看书费
        TbCreditRecord sellerRecord = new TbCreditRecord();
        sellerRecord.setRecordId(UUIDGener.getUUID());
        sellerRecord.setBorrowId(tbBookBorrow.getBorrowId());
        sellerRecord.setRecordTime(new Date());
        sellerRecord.setType(Contents.CreditType.LOOK_IN);
        sellerRecord.setUserId(sellerUserId);
        sellerRecord.setInCash(tbBook.getBorrowCash());
        this.tbCreditRecordDao.insertTbCreditRecord(sellerRecord);

    }

    @Override
    public void lend(String borrowUserId, String sellerUserId, String bookId, String borrowUserKey) throws CheckedException {
        TbBook tbBook = this.tbBookDao.getTbBook(bookId);
        if (Contents.BookState.CAN_BORROW != tbBook.getState()) {
            throw new CheckedException("当前书不可借出");
        }

        TbUser tbUser = this.tbUserDao.getTbUser(borrowUserId);
        if (!tbUser.getBorrowKey().equals(borrowUserKey)) {
            throw new CheckedException("借书码不正确");
        }

        TbUser sellerUser = this.tbUserDao.getTbUser(sellerUserId);

        TbBook tbBookUpdate = new TbBook();
        tbBookUpdate.setBookId(tbBook.getBookId());
        tbBookUpdate.setState(Contents.BookState.LENDED);
        tbBookUpdate.setBorrowCount(tbBook.getBorrowCount() + 1); // 借出次数加1
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

        // 扣钱
        TbUser tbUserUpdate = new TbUser();
        tbUserUpdate.setUserId(borrowUserId);
        tbUserUpdate.setBlance(tbUser.getBlance().subtract(tbBook.getBorrowCash()).subtract(tbBook.getBorrowDeposit()));
        tbUserUpdate.setBorrowCount(tbUser.getBorrowCount() + 1); // 借书次数加1
        this.tbUserDao.updateTbUser(tbUserUpdate);

        // 加钱
        TbUser tbSellerUserUpdate = new TbUser();
        tbSellerUserUpdate.setUserId(sellerUserId);
        tbSellerUserUpdate.setBlance(sellerUser.getBlance().add(tbBook.getBorrowCash()));
        this.tbUserDao.updateTbUser(tbSellerUserUpdate);

        // 增加资金记录
        // 看书费
        TbCreditRecord borrowRecord = new TbCreditRecord();
        borrowRecord.setRecordId(UUIDGener.getUUID());
        borrowRecord.setBorrowId(tbBookBorrow.getBorrowId());
        borrowRecord.setRecordTime(new Date());
        borrowRecord.setType(Contents.CreditType.LOOK_OUT);
        borrowRecord.setUserId(borrowUserId);
        borrowRecord.setOutCash(tbBook.getBorrowCash());
        this.tbCreditRecordDao.insertTbCreditRecord(borrowRecord);

        // 押金费
        borrowRecord.setRecordId(UUIDGener.getUUID());
        borrowRecord.setType(Contents.CreditType.YAJIN_OUT);
        borrowRecord.setUserId(borrowUserId);
        borrowRecord.setOutCash(tbBook.getBorrowDeposit());
        this.tbCreditRecordDao.insertTbCreditRecord(borrowRecord);

        // 增加资金记录
        // 看书费
        TbCreditRecord sellerRecord = new TbCreditRecord();
        sellerRecord.setRecordId(UUIDGener.getUUID());
        sellerRecord.setBorrowId(tbBookBorrow.getBorrowId());
        sellerRecord.setRecordTime(new Date());
        sellerRecord.setType(Contents.CreditType.LOOK_IN);
        sellerRecord.setUserId(sellerUserId);
        sellerRecord.setInCash(tbBook.getBorrowCash());
        this.tbCreditRecordDao.insertTbCreditRecord(sellerRecord);

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

        TbUser borrowUser = this.tbUserDao.getTbUser(tbBookBorrow.getBorrowUserId());
        TbBook tbBookUpdate = new TbBook();
        tbBookUpdate.setBookId(tbBookBorrow.getBookId());
        tbBookUpdate.setState(Contents.BookState.CAN_BORROW);

        // 操作人不是书的借出人
        if (!tbBookBorrow.getSellerUserId().equals(sellerUserId)) {
            // 还至网点
            if (tbBookBorrow.getBackPoint() == 0 && tbBookBorrow.getPointUserId().equals(sellerUserId)) {
                tbBookUpdate.setSellerId(tbBookBorrow.getPointUserId()); // 更换成网点用户
            } else {
                throw new CheckedException("当前书籍不是此所有者");
            }
        }

        TbUser sellerUser = this.tbUserDao.getTbUser(tbBookBorrow.getSellerUserId());

        TbBookBorrow tbBookBorrowUpdate = new TbBookBorrow();
        tbBookBorrowUpdate.setBorrowId(borrowId);
        tbBookBorrowUpdate.setState(Contents.BookBorrowState.BACK);
        tbBookBorrowUpdate.setEndTime(new Date());
        int row = this.tbBookBorrowDao.updateTbBookBorrow(tbBookBorrowUpdate);
        if (row == 0) {
            throw new CheckedException("确认收到还书失败");
        }


        this.tbBookDao.updateTbBook(tbBookUpdate);

        // 过期日期
        int dayExpireDays = this.getExpireDays(tbBookBorrow.getStartTime(), Contents.LOOK_DAYS);
        BigDecimal expireFee = BigDecimal.valueOf(Contents.EXPIRES_FEE * dayExpireDays);

        // 超期给卖家加钱
        if (dayExpireDays > 0) {
            // 增加资金记录
            // 看书费
            TbCreditRecord sellerRecord = new TbCreditRecord();
            sellerRecord.setRecordId(UUIDGener.getUUID());
            sellerRecord.setBorrowId(tbBookBorrow.getBorrowId());
            sellerRecord.setRecordTime(new Date());
            sellerRecord.setType(Contents.CreditType.GUOQI_IN);
            sellerRecord.setUserId(tbBookBorrow.getSellerUserId());
            sellerRecord.setInCash(expireFee);
            this.tbCreditRecordDao.insertTbCreditRecord(sellerRecord);

            // 加钱
            TbUser tbSellerUserUpdate = new TbUser();
            tbSellerUserUpdate.setUserId(tbBookBorrow.getSellerUserId());
            tbSellerUserUpdate.setBlance(sellerUser.getBlance().add(expireFee));
            this.tbUserDao.updateTbUser(tbSellerUserUpdate);
        }

        // 增加资金记录
        // 看书费
        BigDecimal backCash = tbBookBorrow.getBorrowDeposit().subtract(expireFee);
        TbCreditRecord borrowRecord = new TbCreditRecord();
        borrowRecord.setRecordId(UUIDGener.getUUID());
        borrowRecord.setBorrowId(tbBookBorrow.getBorrowId());
        borrowRecord.setRecordTime(new Date());
        borrowRecord.setType(Contents.CreditType.YAJIN_IN);
        borrowRecord.setUserId(borrowUser.getUserId());
        borrowRecord.setInCash(backCash);
        this.tbCreditRecordDao.insertTbCreditRecord(borrowRecord);


        // 加钱
        TbUser borrowUserUpdate = new TbUser();
        borrowUserUpdate.setUserId(borrowUser.getUserId());
        borrowUserUpdate.setBlance(borrowUser.getBlance().add(backCash));
        this.tbUserDao.updateTbUser(borrowUserUpdate);
    }

    @Override
    public TbUser register(TbUser tbUser) throws CheckedException {

        if (this.getUserByName(tbUser.getUserName()) != null) {
            throw new CheckedException("已经存在：" + tbUser.getUserName() + " 的用户");

        }
        tbUser.setUserId(UUIDGener.getUUID());
        tbUser.setRegTime(new Date());
        String borrowKey = UUIDGener.getUUID().substring(0, 6);
        tbUser.setBorrowKey(borrowKey);
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

    private int getExpireDays(Date startTime, int dayLimit) {
        long end = System.currentTimeMillis();
        int dayDiff = (int)Math.ceil((end - startTime.getTime()) / (double)(1000 * 60 * 60 * 24));
        return dayDiff <= dayLimit ? 0 : dayDiff - dayLimit;
    }

}
