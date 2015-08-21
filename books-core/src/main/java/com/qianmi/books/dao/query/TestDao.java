package com.qianmi.books.dao.query;

import com.qianmi.books.dao.base.BaseDao;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with of666.
 * User: of666
 * Date: 2015/8/19 0019
 * Time: 17:51
 */
@Service
public class TestDao extends BaseDao {

    public void queryTest(){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("userId","A745518");
        System.out.println(this.queryForObject("security.UserActLogDao.getOftenIp","A745518"));
    }
}
