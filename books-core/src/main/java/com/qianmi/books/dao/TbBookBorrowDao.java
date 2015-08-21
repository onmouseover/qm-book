package com.qianmi.books.dao;

import com.qianmi.books.dao.domain.TbBookBorrow;

import java.util.List;

public interface TbBookBorrowDao {
    public List<TbBookBorrow> getTbBookBorrowList(TbBookBorrow tbBookBorrow) throws Exception;

    public void insertTbBookBorrow(TbBookBorrow tbBookBorrow) throws Exception;

    public int updateTbBookBorrow(TbBookBorrow tbBookBorrow) throws Exception;

    public int deleteTbBookBorrow(String borrowId) throws Exception;
}