package com.rapidboot.common.base;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 按照数据库设计规范，这三个字段是必须的。
 *
 * @author guangfeng
 * @Date 2019年10月24日20:46:32
 */
@Data
public class BaseEntity implements Serializable {

    /**
     * 删除标识
     */
    private Boolean isDelete;

    private Timestamp createdAt;

    private Timestamp updatedAt;

}
