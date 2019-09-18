package com.zhou.busi.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zhou.busi.common.entity.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 *
 * </p>
 *
 * @author zhoushengyuan
 * @since 2019-01-21
 */
@TableName("link")
@ApiModel(value = "Link类", description = "友情链接类")
public class Link extends BaseModel {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private String id;

    /**
     * logo
     */
    private String logo;

    /**
     * 名称
     */
    @ApiModelProperty(name = "name", value = "网站名称")
    private String name;

    /**
     * 网站
     */
    @ApiModelProperty(name = "site", value = "网站地址")
    private String site;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    @Override
    public String toString() {
        return "Link{" +
                "logo=" + logo +
                ", name=" + name +
                ", site=" + site +
                "}";
    }
}
