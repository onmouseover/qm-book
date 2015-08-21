package com.qianmi.books.dao.base;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapExecutor;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import java.sql.SQLException;
import java.util.List;

/**
 * 
 * 类描述： 
 * 类名称：BaseDao.java   
 * 修改备注：
 * @version     
 *
 */

public abstract class BaseDao extends SqlMapClientDaoSupport {
    /**
     *
    * @Description:
    * @Title: setDefaultSqlMapClient
    * @param sqlMapClient  void 返回类型
    * @throws
    * @since  CodingExample　Ver(编码范例查看) 1.1
     */

    @Autowired
    protected void setDefaultSqlMapClient(@Qualifier("booksdbMapClient")SqlMapClient sqlMapClient) {
        super.setSqlMapClient(sqlMapClient);
    }
    /**
     *
    * @Description:
    * @Title: singleQuery
    * @param identity
    * @param paramObject
    * @return  String 返回类型
    * @throws
    * @since  CodingExample　Ver(编码范例查看) 1.1
     */
    protected String singleQuery(String identity, Object paramObject) {
        return (String) this.getSqlMapClientTemplate().queryForObject(identity, paramObject);
    }

    /**
     * 查询返回单个对象
     *
     * @param identity
     *            查询语句id
     * @param paramObject
     *            查询参数
     * @return
     * @see
     * @since
     */
    public Object queryForObject(String identity, Object paramObject) {
        return this.getSqlMapClientTemplate().queryForObject(identity, paramObject);
    }

    /**
     * 删除记录
     *
     * @param identity
     *            查询语句id
     * @param paramObject
     *            查询参数
     * @return
     * @see
     * @since
     */
    public int delete(String identity, Object paramObject) {
        return this.getSqlMapClientTemplate().delete(identity, paramObject);
    }

    /**
     * 更新记录
     * @param identity
     *            查询语句id
     * @param paramObject
     *            查询参数
     * @return
     * @see
     * @since
     */
    public int update(String identity, Object paramObject) {
        return this.getSqlMapClientTemplate().update(identity, paramObject);
    }

    /**
     * 插入记录
     * @param identity
     *            查询语句id
     * @param paramObject
     *            查询参数
     * @return
     * @see
     * @since
     */
    public Object insert(String identity, Object paramObject) {
        return this.getSqlMapClientTemplate().insert(identity, paramObject);
    }

    /**
     * 查询返回对象列表
     * @param identity
     *            查询语句id
     * @param paramObject
     *            查询参数
     * @return
     * @see
     * @since
     */
    public List<?> queryForList(String identity, Object paramObject) {
        return this.getSqlMapClientTemplate().queryForList(identity, paramObject);
    }

    /**
     * 批量插入
     * @param identity
     *            要执行的sql
     * @param paramList
     *            参数集合
     * @see
     * @since
     */
    public void insertBatch(final String identity, final List<?> paramList) {
        this.getSqlMapClientTemplate().execute(new SqlMapClientCallback<Object>() {
            public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
                executor.startBatch();
                int batch = 0;
                for (int i = 0; i < paramList.size(); i++) {
                    executor.insert(identity, paramList.get(i));
                    // 每50条批量提交一次。
                    batch++;
                    if (batch == 50) {
                        executor.executeBatch();
                        batch = 0;
                    }
                }
                executor.executeBatch();
                return null;
            }
        });
    }

    /**
     * 批量更新
     * @param identity
     *            要执行的sql
     * @param paramList
     *            参数集合
     * @see
     * @since
     */
    public void updateBatch(final String identity, final List<?> paramList) {
        this.getSqlMapClientTemplate().execute(new SqlMapClientCallback<Object>() {
            public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
                executor.startBatch();
                int batch = 0;
                for (int i = 0; i < paramList.size(); i++) {
                    executor.update(identity, paramList.get(i));
                    // 每50条批量提交一次。
                    batch++;
                    if (batch == 50) {
                        executor.executeBatch();
                        batch = 0;
                    }
                }
                executor.executeBatch();
                return null;
            }
        });
    }

    /**
     * 批量删除
     * @param identity
     *            要执行的sql
     * @param paramList
     *            参数集合
     * @see
     * @since
     */
    public void deleteBatch(final String identity, final List<?> paramList) {
        this.getSqlMapClientTemplate().execute(new SqlMapClientCallback<Object>() {
            public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
                executor.startBatch();
                int batch = 0;
                for (int i = 0; i < paramList.size(); i++) {
                    executor.delete(identity, paramList.get(i));
                    // 每50条批量提交一次。
                    batch++;
                    if (batch == 50) {
                        executor.executeBatch();
                        batch = 0;
                    }
                }
                executor.executeBatch();
                return null;
            }
        });
    }

    /**
     * 解析排序
     * @param scolumns
     * @return
     */
    public String getOrderStrForBaseDAO(String scolumns) {
        String orderby = "";
        if (StringUtils.isNotEmpty(scolumns)) {
            String[] scloumnArray = scolumns.split(",");
            for (int i = 0; i < scloumnArray.length; i++) {
                String sclumn = scloumnArray[i];
                sclumn = sclumn.replaceAll("_", " ");
                orderby += i == 0 ? sclumn : "," + sclumn;
            }
        }
        return orderby;
    }
}
