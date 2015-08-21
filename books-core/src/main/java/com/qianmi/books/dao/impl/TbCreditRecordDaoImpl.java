package com.qianmi.books.dao.impl;

import com.qianmi.books.dao.TbCreditRecordDao;
import com.qianmi.books.dao.base.BaseDao;
import com.qianmi.books.dao.domain.TbCreditRecord;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class TbCreditRecordDaoImpl extends BaseDao implements TbCreditRecordDao {
    @Override
    public List<TbCreditRecord> getTbCreditRecordList(TbCreditRecord tbCreditRecord) throws Exception {
        return getSqlMapClientTemplate().queryForList("TB_CREDIT_RECORD.getTbCreditRecordList", tbCreditRecord);
    }

    @Override
    public void insertTbCreditRecord(TbCreditRecord tbCreditRecord) throws Exception {
        getSqlMapClientTemplate().update("TB_CREDIT_RECORD.insertTbCreditRecord", tbCreditRecord);
    }

    @Override
    public int updateTbCreditRecord(TbCreditRecord tbCreditRecord) throws Exception {
        return getSqlMapClientTemplate().update("TB_CREDIT_RECORD.updateTbCreditRecord", tbCreditRecord);
    }

    @Override
    public int deleteTbCreditRecord(String recordId) throws Exception {
        Map parameterMap = new HashMap();
        parameterMap.put("recordId", recordId);
        return getSqlMapClientTemplate().delete("TB_CREDIT_RECORD.deleteTbCreditRecord", parameterMap);
    }
}
