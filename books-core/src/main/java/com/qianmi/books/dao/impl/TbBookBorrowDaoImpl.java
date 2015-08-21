package com.qianmi.books.dao.impl;

import com.qianmi.books.dao.TbBookBorrowDao;
import com.qianmi.books.dao.base.BaseDao;
import com.qianmi.books.dao.domain.TbBookBorrow;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.HashMap;


@Repository
public class TbBookBorrowDaoImpl extends BaseDao implements TbBookBorrowDao {
    @Override
    public List<TbBookBorrow> getTbBookBorrowList(TbBookBorrow tbBookBorrow) {
        return getSqlMapClientTemplate().queryForList("TB_BOOK_BORROW.getTbBookBorrowList", tbBookBorrow);
    }

    @Override
    public void insertTbBookBorrow(TbBookBorrow tbBookBorrow) {
        getSqlMapClientTemplate().update("TB_BOOK_BORROW.insertTbBookBorrow", tbBookBorrow);
    }

    @Override
    public int updateTbBookBorrow(TbBookBorrow tbBookBorrow) {
        return getSqlMapClientTemplate().update("TB_BOOK_BORROW.updateTbBookBorrow", tbBookBorrow);
    }

    @Override
    public int deleteTbBookBorrow(String borrowId) {
        Map parameterMap = new HashMap();
        parameterMap.put("borrowId", borrowId);
        return (Integer) getSqlMapClientTemplate().delete("TB_BOOK_BORROW.deleteTbBookBorrow", parameterMap);
    }

    @Override
    public TbBookBorrow getTbBookBorrow(String borrowId) {
        TbBookBorrow tbBookBorrow = new TbBookBorrow();
        tbBookBorrow.setBorrowId(borrowId);
        return (TbBookBorrow)getSqlMapClientTemplate().queryForObject("TB_BOOK_BORROW.getTbBookBorrowList", tbBookBorrow);
    }
}
