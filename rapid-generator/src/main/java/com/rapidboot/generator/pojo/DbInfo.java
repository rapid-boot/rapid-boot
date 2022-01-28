package com.rapidboot.generator.pojo;

import lombok.Builder;
import lombok.Data;

/**
 * @author guangfeng.liu
 * @date 2022/1/27
 */
@Data
@Builder
public class DbInfo {
    private String url;
    private String username;
    private String password;
}
