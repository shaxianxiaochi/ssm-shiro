package com.empdb.tools;

import java.sql.*;

/**
 * 数据库连接类
 */
public class BaseDao {
    private static String driver = ConfigManager.getInstance().getValue("jdbc.driver");
    private static String url = ConfigManager.getInstance().getValue("jdbc.url");
    private static String username = ConfigManager.getInstance().getValue("jdbc.username");
    private static String password = ConfigManager.getInstance().getValue("jdbc.password");

    /**
     * 获取数据连接
     * @return
     */
    public Connection getConn() {
        Connection conn = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 关闭连接
     * @param rs
     * @param pstmt
     * @param conn
     */
    public void closeAll(ResultSet rs, PreparedStatement pstmt, Connection conn) {

        try {
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 执行增删改
     * @param sql
     * @param objects
     * @return
     */
    public int executeUpdate(String sql, Object... objects) {
        int result = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = getConn();
            pstmt = conn.prepareStatement(sql);
            if (objects != null) {
                for (int i = 0, obj = objects.length; i < obj; i++) {
                    pstmt.setObject(i + 1, objects[i]);
                }
            }
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(null, pstmt, conn);
        }
        return result;
    }
}
