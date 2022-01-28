package com.rapidboot.common.base;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author Zheng Jie
 * @Date 2019年10月24日20:48:53
 */
@Data
public class BaseDTO implements Serializable {

    private Boolean isDelete;

    private Timestamp createTime;

    private Timestamp updateTime;
}
