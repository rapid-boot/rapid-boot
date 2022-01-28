package com.rapidboot.generator;

import com.rapidboot.generator.pojo.ColumnInfo;

/**
 * @author guangfeng.liu
 * @date 2022/1/27
 */
public interface ColumnProcessor {
    /**
     * 处理列
     *
     * @param columnInfo
     */
    void process(ColumnInfo columnInfo);
}
