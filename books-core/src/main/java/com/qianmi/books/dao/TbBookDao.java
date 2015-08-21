package com.qianmi.books.dao;

import com.qianmi.books.dao.domain.TbBook;

import java.util.List;

public interface TbBookDao {
    public List<TbBook> getTbBookList(TbBook tbBook) throws Exception;

    public void insertTbBook(TbBook tbBook) throws Exception;

    public int updateTbBook(TbBook tbBook) throws Exception;

    public int deleteTbBook(String bookId) throws Exception;
}