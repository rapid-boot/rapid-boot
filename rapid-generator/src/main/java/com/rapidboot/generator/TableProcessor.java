package com.rapidboot.generator;

import com.rapidboot.generator.pojo.ColumnInfo;
import com.rapidboot.generator.pojo.TableInfo;

/**
 * @author guangfeng.liu
 * @date 2022/1/27
 */
public interface TableProcessor {
    /**
     * 处理列
     *
     * @param columnInfo
     */
    void process(TableInfo columnInfo);
}
