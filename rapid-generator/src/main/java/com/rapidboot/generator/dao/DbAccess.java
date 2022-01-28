package com.rapidboot.generator.dao;

import com.alibaba.fastjson.JSON;
import com.rapidboot.generator.pojo.ColumnInfo;
import com.rapidboot.generator.pojo.DbInfo;
import com.rapidboot.generator.pojo.TableInfo;
import lombok.extern.java.Log;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;


/**
 * @author guangfeng.liu
 * @date 2022/1/27
 */
@Log
public class DbAccess<getTables> {

    private static Connection getConnection() {
        DbInfo dbInfo = DbInfo.builder().url("jdbc:mysql://localhost:3306/yoma?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=UTF-8").username("root").build();
        try {
            Connection connection = DriverManager.getConnection(dbInfo.getUrl(), dbInfo.getUsername(), dbInfo.getPassword());
            return connection;
        } catch (Exception e) {
            log.warning("DbAccess.getConnection error: " + e.getStackTrace());
        }
        return null;
    }

    public static List<TableInfo> getTables() {
        Connection conn = null;
        PreparedStatement stmt = null;
        List<TableInfo> tableInfos = new LinkedList<>();
        try {
            conn = getConnection();
            String sql = "select table_name ,create_time , engine, table_collation, table_comment from information_schema.tables " + "where table_schema = (select database()) " + "order by create_time desc";
            stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                TableInfo tableInfo = new TableInfo();
                tableInfo.setName(rs.getString("table_name"));
                tableInfo.setCreateTime(rs.getDate("create_time"));
                tableInfo.setEngine(rs.getString("engine"));
                tableInfo.setCollation(rs.getString("table_collation"));
                tableInfo.setComment(rs.getString("table_comment"));
                tableInfos.add(tableInfo);
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se2) {
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        log.info("getTables results: \n" + JSON.toJSONString(tableInfos, true));
        return tableInfos;
    }


    public static List<ColumnInfo> getColumnInfo(String tableName) {
        if (Objects.isNull(tableName) || tableName.length() == 0) {
            log.warning("getTableInfo params null");
            return null;
        }
        Connection conn = null;
        PreparedStatement stmt = null;
        List<ColumnInfo> columnInfos = new LinkedList<>();
        try {
            conn = getConnection();

            String sql = "select column_name, is_nullable, data_type, column_comment, column_key, extra from information_schema.columns " + "where table_name = ? and table_schema = (select database()) order by ordinal_position";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, tableName);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ColumnInfo columnInfo = new ColumnInfo();
                columnInfo.setTableName(tableName);
                columnInfo.setColumnName(rs.getString("column_name"));
                columnInfo.setNotNull(!rs.getBoolean("is_nullable"));
                columnInfo.setColumnType(rs.getString("data_type"));
                columnInfo.setRemark(rs.getString("column_comment"));
                columnInfo.setExtra(rs.getString("extra"));
                columnInfo.setColumnKey(rs.getString("column_key"));
                columnInfos.add(columnInfo);
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se2) {
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        log.fine("get columnInfos results: \n" + JSON.toJSONString(columnInfos, true));
        return columnInfos;
    }

}
