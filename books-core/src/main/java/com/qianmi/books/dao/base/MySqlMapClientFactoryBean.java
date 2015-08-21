package com.qianmi.books.dao.base;

import com.ibatis.common.xml.Nodelet;
import com.ibatis.common.xml.NodeletException;
import com.ibatis.common.xml.NodeletParser;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.engine.builder.xml.SqlMapConfigParser;
import com.ibatis.sqlmap.engine.builder.xml.SqlMapParser;
import com.ibatis.sqlmap.engine.builder.xml.SqlStatementParser;
import com.ibatis.sqlmap.engine.builder.xml.XmlParserState;
import com.ibatis.sqlmap.engine.execution.SqlExecutor;
import com.ibatis.sqlmap.engine.impl.SqlMapClientImpl;
import com.ibatis.sqlmap.engine.impl.SqlMapExecutorDelegate;
import com.ibatis.sqlmap.engine.mapping.statement.MappedStatement;
import com.ibatis.sqlmap.engine.mapping.statement.RowHandlerCallback;
import com.ibatis.sqlmap.engine.mapping.statement.SelectStatement;
import com.ibatis.sqlmap.engine.scope.ErrorContext;
import com.ibatis.sqlmap.engine.scope.SessionScope;
import com.ibatis.sqlmap.engine.scope.StatementScope;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.NestedIOException;
import org.springframework.core.io.Resource;
import org.springframework.orm.ibatis.SqlMapClientFactoryBean;
import org.springframework.util.ObjectUtils;
import org.w3c.dom.Node;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.sql.*;
import java.util.Properties;


/**
 * <p>
 * 扩展SqlMapClientFactoryBean
 * 
 * 1. 统一分页处理。
 * 
 * 2. 修改SqlMapConfig文件检查问题，支持sqlmap-mapping文件的通配
 * </p>
 */
public class MySqlMapClientFactoryBean extends SqlMapClientFactoryBean {

    /**
     * Content of logger
     */
    protected static Logger logger = LoggerFactory.getLogger(MySqlMapClientFactoryBean.class);

    private PageQuery pageQuery;

    /**
     * 初始化方法
     * 
     * @throws Exception
     *             可能发生的异常
     */
    public void afterPropertiesSet() throws Exception {
        if (pageQuery == null) {
            throw new IllegalArgumentException("pageQuery is required");
        }

        super.afterPropertiesSet();
    }

    /**
     * Build a SqlMapClient instance based on the given standard configuration.
     * 
     * @param configLocations
     *            Resource[]
     * @param mappingLocations
     *            Resource[]
     * @param properties
     *            Properties
     * @return client
     * @throws java.io.IOException
     *             IOException
     */
    protected SqlMapClient buildSqlMapClient(Resource[] configLocations, Resource[] mappingLocations,
            Properties properties) throws IOException {
        logger.debug("MySqlMapClientFactoryBean.buildSqlMapClient() IN");
        logger.debug("configLocations is: {},mappingLocations is: {},properties is: {}", new Object[] {
                configLocations, mappingLocations, properties });
        if (ObjectUtils.isEmpty(configLocations)) {
            throw new IllegalArgumentException("At least 1 'configLocation' entry is required");
        }

        SqlMapClient client = null;
        SqlMapConfigParser configParser = new SqlMapConfigParser();

        // Solve SqlMapMapping Bugs
        try {
            final Field parser = SqlMapConfigParser.class.getDeclaredField("parser");
            logger.debug("parser is: {}", parser);
            // 规避findbugs安全检查
            AccessController.doPrivileged(new PrivilegedAction<Object>() {

                public Object run() {
                    // 暴力访问受保护属性
                    parser.setAccessible(true);
                    return null;
                }

            });

            NodeletParser nodeletParser = (NodeletParser) parser.get(configParser);
            logger.debug("nodeletParser is: {}", nodeletParser);
            // 修改SqlMapConfig文件校验问题
            nodeletParser.setValidation(false);

        } catch (Exception ex) {
            logger.error(
                    "MySqlMapClientFactoryBean.buildSqlMapClient() is fail,configLocations is: {},mappingLocations is: {},properties is: {},error is: {}",
                    new Object[] { configLocations, mappingLocations, properties, ex });
            throw new IllegalStateException("iBATIS 2.3.2 'state' field not found in SqlMapConfigParser class - "
                    + "please upgrade to IBATIS 2.3.2 or higher in order to use the new 'mappingLocations' feature. "
                    + ex);
        }

        for (Resource configLocation : configLocations) {
            InputStream is = configLocation.getInputStream();
            try {
                client = configParser.parse(is, properties);
                logger.debug("is = {},client is: {}", is, client);
            } catch (RuntimeException ex) {
                logger.error("MySqlMapClientFactoryBean.buildSqlMapClient() is fail,error is: {}", ex);
                throw new NestedIOException("Failed to parse config resource: " + configLocation, ex);
            }
        }

        if (mappingLocations != null) {
            SqlMapParser mapParser = MySqlMapParserFactory.createSqlMapParser(configParser, pageQuery);
            for (Resource mappingLocation : mappingLocations) {
                try {
                    mapParser.parse(mappingLocation.getInputStream());
                } catch (NodeletException ex) {
                    logger.error("MySqlMapClientFactoryBean.buildSqlMapClient() is fail,error is: {}", ex);
                    throw new NestedIOException("Failed to parse mapping resource: " + mappingLocation, ex);
                }
            }
        }
        logger.debug("MySqlMapClientFactoryBean.buildSqlMapClient() OUT");
        return client;
    }

    /**
     * Inner class to avoid hard-coded iBATIS 2.3.2 dependency (XmlParserState
     * class).
     */
    private static class MySqlMapParserFactory {
        private MySqlMapParserFactory() {
        }

        public static SqlMapParser createSqlMapParser(SqlMapConfigParser configParser, PageQuery query) {
            // Ideally: XmlParserState state = configParser.getState();
            // Should raise an enhancement request with iBATIS...
            XmlParserState state = null;
            try {
                final Field stateField = SqlMapConfigParser.class.getDeclaredField("state");

                AccessController.doPrivileged(new PrivilegedAction<Object>() {

                    public Object run() {
                        stateField.setAccessible(true);
                        return null;
                    }
                });

                state = (XmlParserState) stateField.get(configParser);
            } catch (Exception ex) {
                throw new IllegalStateException(
                        "iBATIS 2.3.2 'state' field not found in SqlMapConfigParser class - "
                                + "please upgrade to IBATIS 2.3.2 or higher in order to use the new 'mappingLocations' feature. "
                                + ex);
            }
            return new MySqlMapParser(state, query);
        }
    }

    /**
     * <p>
     * Ext SqlMapParser
     * </p>
     *
     * @author wuwen
     */
    private static class MySqlMapParser extends SqlMapParser {
        private PageQuery pageQuery;

        public MySqlMapParser(XmlParserState state, PageQuery pq) {
            super(state);
            this.pageQuery = pq;
        }

        protected void addStatementNodelets() {
            super.addStatementNodelets();

            NodeletParser parser = null;
            final SqlStatementParser statementParser;

            try {
                final Field parserField = SqlMapParser.class.getDeclaredField("parser");

                AccessController.doPrivileged(new PrivilegedAction<Object>() {

                    public Object run() {
                        parserField.setAccessible(true);
                        return null;
                    }

                });

                parser = (NodeletParser) parserField.get(this);

                final Field statementParserField = SqlMapParser.class.getDeclaredField("statementParser");

                AccessController.doPrivileged(new PrivilegedAction<Object>() {

                    public Object run() {
                        statementParserField.setAccessible(true);
                        return null;
                    }

                });

                statementParser = (SqlStatementParser) statementParserField.get(this);
            } catch (Exception ex) {
                throw new IllegalStateException("iBATIS 2.3.2 'state' field not found in SqlMapParser class - "
                        + "please upgrade to IBATIS 2.3.2 or higher in order to "
                        + "use the new 'mappingLocations' feature. " + ex);
            }

            parser.addNodelet("/sqlMap/select", new Nodelet() {
                public void process(Node node) {
                    statementParser.parseGeneralStatement(node, new SelectStatement() {
                        /**
                         * extends SelectStatement
                         *
                         * @return SqlExecutor
                         */
                        public SqlExecutor getSqlExecutor() {
                            return new MySqlExecutor(super.getSqlExecutor(), pageQuery);
                        }
                    });
                }
            });
        }
    }

    /**
     * <p>
     * <A sentence of brief function description>
     * </p>
     *
     * @author wuwen
     */
    private static class MySqlExecutor extends SqlExecutor {
        private final SqlExecutor sqlExecutor;

        private final PageQuery pageQuery;

        /**
         * constructor
         */
        public MySqlExecutor(SqlExecutor sqlExecutor, PageQuery pageQuery) {
            this.sqlExecutor = sqlExecutor;
            this.pageQuery = pageQuery;
        }

        @Override
        public void executeQuery(StatementScope statementScope, Connection conn, String sql, Object[] parameters,
                int skipResults, int maxResults, RowHandlerCallback callback) throws SQLException {
            logger.debug("MySqlExecutor.executeQuery() IN");
            logger.debug("statementScope is: {},conn is: {},callback is: {}", new Object[] { statementScope, conn,
                    callback });
            logger.debug("sql = {},maxResults = {}", new Object[] { sql, maxResults });
            // String statementId = statementScope.getStatement().getId();

            String newSql = sql;

            if (PageQueryHelper.isPageQuery()) {
                newSql = pageQuery.getCountSql(sql);

                Integer count = this.executeCount(statementScope, conn, newSql, parameters);

                PageQueryHelper.getPageInfo().setTotalRows(count);

                // 从分页工具中获取当前请求页,
                Page pageInfo = PageQueryHelper.getPageInfo();
                int curPage = pageInfo.getCurPage();
                int perSize = pageInfo.getPerSize();

                newSql = pageQuery.getPagedQuerySql(sql, curPage, perSize);

                logger.debug("newSql = {},count = {}", newSql, count);
            }

            // System.out.println(statementId);

            this.sqlExecutor.executeQuery(statementScope, conn, newSql, parameters, skipResults, maxResults, callback);
            logger.debug("MySqlExecutor.executeQuery() OUT");
        }

        /**
         * Long form of the method to execute a query
         *
         * @param statementScope
         *            - the request scope
         * @param conn
         *            - the database connection
         * @param sql
         *            - the SQL statement to execute
         * @param parameters
         *            - the parameters for the statement
         * @param skipResults
         *            - the number of results to skip
         * @param maxResults
         *            - the maximum number of results to return
         * @param callback
         *            - the row handler for the query
         * @throws java.sql.SQLException
         *             - if the query fails
         */
        public Integer executeCount(StatementScope statementScope, Connection conn, String sql, Object[] parameters)
                throws SQLException {
            logger.debug("MySqlExecutor.executeCount() IN");
            logger.debug("statementScope is: {},conn is: {},sql = {}, parameters is: {}", new Object[] {
                    statementScope, conn, sql, parameters });

            ErrorContext errorContext = statementScope.getErrorContext();
            errorContext.setActivity("executing query");
            errorContext.setObjectId(sql);
            PreparedStatement ps = null;
            ResultSet rs = null;

            Integer count = null;

            try {
                errorContext.setMoreInfo("Check the SQL Statement (preparation failed).");

                ps = prepareStatement(statementScope.getSession(), conn, sql);

                setStatementTimeout(statementScope.getStatement(), ps);
                Integer fetchSize = statementScope.getStatement().getFetchSize();
                if (fetchSize != null) {
                    ps.setFetchSize(fetchSize.intValue());
                }
                errorContext.setMoreInfo("Check the parameters (set parameters failed).");
                statementScope.getParameterMap().setParameters(statementScope, ps, parameters);
                errorContext.setMoreInfo("Check the statement (query failed).");
                ps.execute();
                errorContext.setMoreInfo("Check the results (failed to retrieve results).");

                // Begin ResultSet Handling
                rs = ps.getResultSet();
                logger.debug("rs is: {},ps is: {}", rs, ps);
                if (rs != null) {
                    if (rs.next()) {
                        count = rs.getInt(1);
                    }
                }
                logger.debug("count = {}", count);
                logger.debug("MySqlExecutor.executeCount() OUT");
                return count;
                // End ResultSet Handling
            } finally {
                try {
                    closeResultSet(rs);
                } finally {
                    closeStatement(statementScope.getSession(), ps);
                }
            }

        }

        private static void setStatementTimeout(MappedStatement mappedStatement, Statement statement)
                throws SQLException {
            logger.debug("MySqlExecutor.setStatementTimeout() IN");
            logger.debug("mappedStatement is: {},statement is: {}", mappedStatement, statement);
            if (mappedStatement.getTimeout() != null) {
                statement.setQueryTimeout(mappedStatement.getTimeout().intValue());
            }
            logger.debug("MySqlExecutor.setStatementTimeout() OUT");
        }

        /**
         * close result set
         * 
         * @param rs
         */
        private static void closeResultSet(ResultSet rs) {
            logger.debug("MySqlExecutor.closeResultSet() IN");
            logger.debug("rs is: {}", rs);
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    // ignore
                    logger.warn("MySqlExecutor.closeResultSet() is fail,rs is: {},error is: {}", rs, e);
                }
            }
            logger.debug("MySqlExecutor.closeResultSet() OUT");

        }

        private static void closeStatement(SessionScope sessionScope, PreparedStatement ps) {
            logger.debug("MySqlExecutor.closeStatement() IN");
            logger.debug("sessionScope is: {},ps is: {}", new Object[] { sessionScope, ps });
            if (ps != null) {
                if (!sessionScope.hasPreparedStatement(ps)) {
                    try {
                        ps.close();
                    } catch (SQLException e) {
                        // ignore
                        logger.warn("MySqlExecutor.closeStatement() is fail,error is: {}", e);
                    }
                }
            }
            logger.debug("MySqlExecutor.closeStatement() OUT");
        }

        private static PreparedStatement prepareStatement(SessionScope sessionScope, Connection conn, String sql)
                throws SQLException {
            logger.debug("MySqlExecutor.prepareStatement() IN");
            logger.debug("sessionScope is: {},conn is: {},sql = {}", new Object[] { sessionScope, conn, sql });
            SqlMapExecutorDelegate delegate = ((SqlMapClientImpl) sessionScope.getSqlMapExecutor()).getDelegate();
            logger.debug("delegate is: {}", delegate);
            if (sessionScope.hasPreparedStatementFor(sql)) {
                logger.debug("sessionScope.getPreparedStatement(sql) is: {}", sessionScope.getPreparedStatement(sql));
                logger.debug("MySqlExecutor.prepareStatement() OUT");
                return sessionScope.getPreparedStatement(sql);
            } else {
                PreparedStatement ps = conn.prepareStatement(sql);
                sessionScope.putPreparedStatement(delegate, sql, ps);

                logger.debug("ps is: {}", ps);
                logger.debug("MySqlExecutor.prepareStatement() OUT");
                return ps;
            }
        }

    }

    /**
     * @return the pageQuery
     */
    public PageQuery getPageQuery() {
        return pageQuery;
    }

    /**
     * @param pageQuery
     *            the pageQuery to set
     */
    public void setPageQuery(PageQuery pageQuery) {
        this.pageQuery = pageQuery;
    }

}
