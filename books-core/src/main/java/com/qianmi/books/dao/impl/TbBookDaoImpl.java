package com.qianmi.books.dao.impl;

import com.qianmi.books.dao.TbBookDao;
import com.qianmi.books.dao.base.BaseDao;
import com.qianmi.books.dao.domain.TbBook;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class TbBookDaoImpl extends BaseDao implements TbBookDao {
    @Override
    public List<TbBook> getTbBookList(TbBook tbBook) throws Exception {
        return getSqlMapClientTemplate().queryForList("TB_BOOK.getTbBookList", tbBook);
    }

    @Override
    public void insertTbBook(TbBook tbBook) throws Exception {
        getSqlMapClientTemplate().update("TB_BOOK.insertTbBook", tbBook);
    }

    @Override
    public int updateTbBook(TbBook tbBook) throws Exception {
        return getSqlMapClientTemplate().update("TB_BOOK.updateTbBook", tbBook);
    }

    @Override
    public int deleteTbBook(String bookId) throws Exception {
        Map parameterMap = new HashMap();
        parameterMap.put("bookId", bookId);
        return getSqlMapClientTemplate().delete("TB_BOOK.deleteTbBook", parameterMap);
    }
}
