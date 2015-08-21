package com.qianmi.books.dao.impl;

import com.qianmi.books.dao.TbBookDao;
import com.qianmi.books.dao.base.BaseDao;
import com.qianmi.books.dao.domain.TbBook;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

@Repository
public class TbBookDaoImpl extends BaseDao implements TbBookDao {
    @Override
    public List<TbBook> getTbBookList(TbBook tbBook) {
        return getSqlMapClientTemplate().queryForList("TB_BOOK.getTbBookList", tbBook);
    }

    @Override
    public void insertTbBook(TbBook tbBook) {
        getSqlMapClientTemplate().update("TB_BOOK.insertTbBook", tbBook);
    }

    @Override
    public int updateTbBook(TbBook tbBook) {
        return getSqlMapClientTemplate().update("TB_BOOK.updateTbBook", tbBook);
    }

    @Override
    public int deleteTbBook(String bookId) {
        Map parameterMap = new HashMap();
        parameterMap.put("bookId", bookId);
        return getSqlMapClientTemplate().delete("TB_BOOK.deleteTbBook", parameterMap);
    }

    @Override
    public TbBook getTbBook(String bookId) {
        TbBook tbBook = new TbBook();
        tbBook.setBookId(bookId);
        return (TbBook)getSqlMapClientTemplate().queryForObject("TB_BOOK.getTbBookList", tbBook);
    }
}
