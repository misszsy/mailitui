package com.zhou.busi.entity;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zhou.busi.common.entity.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 *
 * </p>
 *
 * @author zhoushengyuan
 * @since 2018-12-24
 */
@TableName("article")
@ApiModel(value = "Article类",description = "文章资讯类")
public class Article extends BaseModel {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private String id;

    /**
     * 栏目id
     */
    @ApiModelProperty(name = "columnId",value = "栏目id")
    private String columnId;

    /**
     * 文章类型
     */
    @ApiModelProperty(name = "typeId",value = "文章类型")
    private String typeId;

    /**
     * 文章标题
     */
    @ApiModelProperty(name = "title",value = "文章标题")
    private String title;

    /**
     * 文章图片
     */
    @ApiModelProperty(name = "picture",value = "文章图片")
    private String picture;

    /**
     * 关键字
     */
    @ApiModelProperty(name = "keyword",value = "关键字")
    private String keyword;

    /**
     * 描述说明
     */
    @ApiModelProperty(name = "description",value = "描述说明")
    private String description;

    /**
     * 文章来源
     */
    @ApiModelProperty(name = "source",value = "文章来源")
    private String source;

    /**
     * 文章状态(0:未发布,1:已发布,2:已取消)
     */
    @ApiModelProperty(name = "status",value = "文章状态(0:未发布,1:已发布,2:已取消)")
    private String status;

    /**
     * 是否推荐(0:否,1:是)
     */
    @ApiModelProperty(name = "recommend",value = "是否推荐(0:否,1:是)")
    private String recommend;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createDate;

    /**
     * 发布人
     */
    private String publishBy;

    /**
     * 发布时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(name = "publishDate",value = "发布时间")
    private LocalDateTime publishDate;

    /**
     * 文章内容
     */
    @ApiModelProperty(name = "content",value = "文章内容")
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

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getRecommend() {
        return recommend;
    }

    public void setRecommend(String recommend) {
        this.recommend = recommend;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Article{" +
        "columnId=" + columnId +
        ", typeId=" + typeId +
        ", title=" + title +
        ", picture=" + picture +
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
