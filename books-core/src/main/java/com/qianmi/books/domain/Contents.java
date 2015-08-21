package com.qianmi.books.domain;

/**
 * Created by caowei on 15-8-21.
 */
public interface Contents {
    /**
     * 借书期限为30天
     */
    public static final int LOOK_DAYS = 30;

    /**
     * 超期的每天费用
     */
    public static final double EXPIRES_FEE = 1;


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

    public interface CreditType {
        /**
         * 押金支出
         */
        int YAJIN_OUT = 1;
        /**
         * 看书支出
         */
        int LOOK_OUT = 2;
        /**
         * 过期扣除
         */
        int GUOQI_OUT = 3;
        /**
         * 提现支出
         */
        int WITHDRAW = 4;
        /**
         * 押金收入
         */
        int YAJIN_IN = 11;
        /**
         * 看书收入
         */
        int LOOK_IN = 12;
        /**
         * 过期收入
         */
        int GUOQI_IN = 13;
        /**
         * 充值
         */
        int DEPOSIT = 14;

    }


}
