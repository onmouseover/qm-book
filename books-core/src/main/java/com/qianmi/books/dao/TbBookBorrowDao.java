package com.qianmi.books.dao;

import com.qianmi.books.dao.domain.TbBookBorrow;

import java.util.List;

public interface TbBookBorrowDao {
    public List<TbBookBorrow> getTbBookBorrowList(TbBookBorrow tbBookBorrow);

    public TbBookBorrow getTbBookBorrow(String borrowId);

    public void insertTbBookBorrow(TbBookBorrow tbBookBorrow);

    public int updateTbBookBorrow(TbBookBorrow tbBookBorrow);

    public int deleteTbBookBorrow(String borrowId);
}