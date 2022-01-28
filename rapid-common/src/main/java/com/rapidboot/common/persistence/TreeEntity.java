/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.rapidboot.common.persistence;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 树状结构的实体父类
 *
 * @author 马世豪
 * @version 2019-09-02
 */
@Data
public abstract class TreeEntity<T> extends DataEntity<T> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "显示名称")
    private String name;

    @ApiModelProperty(value = "父级id")
    private Long parentId;

    @ApiModelProperty(value = "层级")
    private Integer level;

    @ApiModelProperty(value = "子级list")
    private List<T> children;


    @ApiModelProperty(value = "是否叶子节点")
    private Boolean isLeaf;


    /**
     * 重写get方法
     *
     * @return
     */
    public Boolean getIsLeaf() {
        if (this.children == null || this.children.size() == 0) {
            isLeaf = true;
        } else {
            isLeaf = false;
        }
        return isLeaf;
    }

}
