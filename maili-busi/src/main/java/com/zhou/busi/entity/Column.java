package com.zhou.busi.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zhou.busi.common.entity.BaseModel;

import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhoushengyuan
 * @since 2018-12-24
 */
@TableName("sys_column")
public class Column extends BaseModel {

    private static final long serialVersionUID = 1L;

    /**
     * 栏目名称
     */
    private String name;

    /**
     * 英文名称
     */
    private String enName;

    /**
     * 父id
     */
    private String parentId;

    /**
     * 排序
     */
    private Integer sort;

    @TableField(exist = false)
    private List<Column> children;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }


    public List<Column> getChildren() {
        return children;
    }

    public void setChildren(List<Column> children) {
        this.children = children;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    @Override
    public String toString() {
        return "Column{" +
        "name=" + name +
        ", enName=" + enName +
        ", parentId=" + parentId +
        ", sort=" + sort +
        "}";
    }
}
