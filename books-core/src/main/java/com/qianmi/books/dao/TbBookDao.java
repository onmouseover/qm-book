package com.qianmi.books.dao;

import com.qianmi.books.dao.domain.TbBook;

import java.util.List;

public interface TbBookDao {
    public List<TbBook> getTbBookList(TbBook tbBook);

    public TbBook getTbBook(String bookId);

    public void insertTbBook(TbBook tbBook);

    public int updateTbBook(TbBook tbBook);

    public int deleteTbBook(String bookId);
}