package com.rapidboot.generator.pojo;
import lombok.Data;
/**
 * @author guangfeng.liu
 * @date 2022/1/27
 */

@Data
public class ProjectInfo {
    private String packageName;
    private Boolean queryHasBigDecimal;
    private Boolean auto;
    private String date;
    private String author;
}
