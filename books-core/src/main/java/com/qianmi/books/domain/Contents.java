package com.qianmi.books.domain;

/**
 * Created by caowei on 15-8-21.
 */
public interface Contents {
    public interface BookState {
        /**
         * 停止出借
         */
        int STOP = 0;
        /**
         * 可借阅
         */
        int CAN_BORROW = 1;
        /**
         * 已预约
         */
        int APPLY = 2;
        /**
         * 已借出
         */
        int LENDED = 3;
    }

    public interface BookBorrowState {
        /**
         * 未归还
         */
        int UNBACK = 0;
        /**
         * 已归还
         */
        int BACK = 1;

    }

    public interface BookType {
        /**
         * 文学
         */
        int WENXUE = 1;
        /**
         * 技术
         */
        int JISHU = 2;
        /**
         * 历史
         */
        int LISHI = 3;
        /**
         * 其他
         */
        int OTHER = 4;
    }
}