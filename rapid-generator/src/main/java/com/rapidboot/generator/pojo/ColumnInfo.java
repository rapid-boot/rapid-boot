package com.rapidboot.generator.pojo;

import lombok.Data;


/**
 * @author guangfeng
 */
@Data
public class ColumnInfo {
    private Long id;
    private String tableName;
    private String columnName;
    private String columnType;
    private String keyType;
    private String extra;
    private String remark;
    //    private Boolean isNotNull;
    private Boolean listShow;
    private Boolean formShow;
    private String formType;
    private String queryType;
    private String dictName;
    private String dateAnnotation;


    private Boolean columnShow;
    private String changeColumnName;
    private String columnKey;
    private Boolean notNull;
    private String capitalColumnName;

}
