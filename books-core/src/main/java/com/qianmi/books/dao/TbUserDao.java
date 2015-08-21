package com.qianmi.books.dao;

import com.qianmi.books.dao.domain.TbUser;

import java.util.List;

public interface TbUserDao {
    public List<TbUser> getTbUserList(TbUser tbUser);
    public TbUser getTbUser(String userId);

    public Integer insertTbUser(TbUser tbUser);

    public Integer updateTbUser(TbUser tbUser);

    public Integer deleteTbUser(String userId);
}