package com.zhou.busi.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zhou.busi.common.entity.BaseModel;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * <p>
 * 预约
 * </p>
 *
 * @author zhoushengyuan
 * @since 2019-01-04
 */
@TableName("contact")
public class Contact extends BaseModel {

    private static final long serialVersionUID = 1L;

    /**
     * 预约名称
     */
    private String name;

    /**
     * 预约手机
     */
    private String mobile;

    /**
     * 创建时间
     */
    private LocalDateTime createDate;

    /**
     * 删除标志
     */
    @TableLogic
    private String disabled;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getDisabled() {
        return disabled;
    }

    public void setDisabled(String disabled) {
        this.disabled = disabled;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "name='" + name + '\'' +
                ", mobile='" + mobile + '\'' +
                ", createDate=" + createDate +
                ", disabled='" + disabled + '\'' +
                '}';
    }
}
