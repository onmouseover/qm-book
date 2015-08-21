package com.qianmi.books.dao;

import com.qianmi.books.dao.domain.TbUser;

import java.util.List;

public interface TbUserDao {
    public List<TbUser> getTbUserList(TbUser tbUser) throws Exception;

    public Integer insertTbUser(TbUser tbUser) throws Exception;

    public Integer updateTbUser(TbUser tbUser) throws Exception;

    public Integer deleteTbUser(String userId) throws Exception;
}