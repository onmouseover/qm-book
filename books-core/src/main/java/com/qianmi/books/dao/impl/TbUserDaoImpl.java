package com.qianmi.books.dao.impl;

import com.qianmi.books.dao.TbUserDao;
import com.qianmi.books.dao.base.BaseDao;
import com.qianmi.books.dao.domain.TbUser;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

@Repository
public class TbUserDaoImpl extends BaseDao implements TbUserDao {
    @Override
    public List<TbUser> getTbUserList(TbUser tbUser) {
        return getSqlMapClientTemplate().queryForList("TB_USER.getTbUserList", tbUser);
    }

    @Override
    public Integer insertTbUser(TbUser tbUser) {
        return (Integer) getSqlMapClientTemplate().update("TB_USER.insertTbUser", tbUser);
    }

    @Override
    public Integer updateTbUser(TbUser tbUser) {
        return getSqlMapClientTemplate().update("TB_USER.updateTbUser", tbUser);
    }

    @Override
    public Integer deleteTbUser(String userId) {
        Map parameterMap = new HashMap();
        parameterMap.put("userId", userId);
        return (Integer) getSqlMapClientTemplate().delete("TB_USER.deleteTbUser", parameterMap);
    }
}
