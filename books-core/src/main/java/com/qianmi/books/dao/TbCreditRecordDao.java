package com.qianmi.books.dao;

import com.qianmi.books.dao.domain.TbCreditRecord;

import java.util.List;

public interface TbCreditRecordDao {
    public List<TbCreditRecord> getTbCreditRecordList(TbCreditRecord tbCreditRecord);

    public TbCreditRecord getTbCreditRecord(String recordId);

    public void insertTbCreditRecord(TbCreditRecord tbCreditRecord);

    public int updateTbCreditRecord(TbCreditRecord tbCreditRecord);

    public int deleteTbCreditRecord(String recordId);
}