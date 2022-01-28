package com.rapidboot.common.exception;

import lombok.Getter;

/**
 * @author Zheng Jie
 * @date 2018-11-23
 * 统一异常处理
 */
@Getter
public class BadRequestException extends RuntimeException {

    private Integer status = 400;

    public BadRequestException(String msg) {
        super(msg);
    }

}
