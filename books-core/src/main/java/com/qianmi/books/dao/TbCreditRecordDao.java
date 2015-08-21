package com.qianmi.books.dao;

import com.qianmi.books.dao.domain.TbCreditRecord;

import java.util.List;

public interface TbCreditRecordDao {
    public List<TbCreditRecord> getTbCreditRecordList(TbCreditRecord tbCreditRecord) throws Exception;

    public void insertTbCreditRecord(TbCreditRecord tbCreditRecord) throws Exception;

    public int updateTbCreditRecord(TbCreditRecord tbCreditRecord) throws Exception;

    public int deleteTbCreditRecord(String recordId) throws Exception;
}