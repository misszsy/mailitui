package com.zhou.busi.entity;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zhou.busi.common.entity.BaseModel;

/**
 * <p>
 *
 * </p>
 *
 * @author zhoushengyuan
 * @since 2018-12-24
 */
@TableName("news")
public class News extends BaseModel {

    private static final long serialVersionUID = 1L;

    /**
     * 栏目id
     */
    private String columnId;

    /**
     * 新闻标题
     */
    private String title;

    /**
     * 关键字
     */
    private String keyword;

    /**
     * 描述说明
     */
    private String description;

    /**
     * 资讯来源
     */
    private String source;

    /**
     * 资讯状态(0:未发布,1:已发布,2:已取消)
     */
    private String status;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 创建时间
     */
    private LocalDateTime createDate;

    /**
     * 发布人
     */
    private String publishBy;

    /**
     * 发布时间
     */
    private LocalDateTime publishDate;

    /**
     * 资讯内容
     */
    private String content;

    /**
     * 删除标志
     */
    @TableLogic
    private String disabled;

    public String getColumnId() {
        return columnId;
    }

    public void setColumnId(String columnId) {
        this.columnId = columnId;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }
    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public String getPublishBy() {
        return publishBy;
    }

    public void setPublishBy(String publishBy) {
        this.publishBy = publishBy;
    }

    public LocalDateTime getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDateTime publishDate) {
        this.publishDate = publishDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    public String getDisabled() {
        return disabled;
    }

    public void setDisabled(String disabled) {
        this.disabled = disabled;
    }

    @Override
    public String toString() {
        return "News{" +
        "columnId=" + columnId +
        ", title=" + title +
        ", keyword=" + keyword +
        ", description=" + description +
        ", source=" + source +
        ", status=" + status +
        ", createBy=" + createBy +
        ", createDate=" + createDate +
        ", publishBy=" + publishBy +
        ", publishDate=" + publishDate +
        ", content=" + content +
        ", disabled=" + disabled +
        "}";
    }
}
