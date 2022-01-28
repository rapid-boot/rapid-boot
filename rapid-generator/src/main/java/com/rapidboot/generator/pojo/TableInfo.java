package com.rapidboot.generator.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.sql.Date;
import java.util.List;
import java.util.Map;

/**
 * @author guangfeng
 */
@Data
public class TableInfo {
    private String name;
    private Date createTime;
    private String engine;
    private String collation;
    private String comment;

    private List<ColumnInfo> columns;


    /**
     * 改变 json key
     */
    private String packageName;
    private Boolean queryHasBigDecimal;
    private Boolean auto;
    private String date;
    private String author;


    private String moduleName;
    private String apiAlias;
    private String pkColumnType;
    private String className;
    private Boolean hasBigDecimal;
    private String pkCapitalColName;
    private Boolean hasQuery;
    private String tableName;
    private Boolean hasTimestamp;

    private Boolean hasDict;
    private List<Map<String, String>> dicts;
    private List<Map<String, String>> queryColumns;
    private List<Map<String, String>> isNotNullColumns;
    private List<Map<String, String>> betweens;


    private String changeClassName;
    private Boolean hasDateAnnotation;
    private Boolean queryHasTimestamp;
    private String pkChangeColName;
}
