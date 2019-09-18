package com.zhou.busi.service;

import com.zhou.busi.entity.Column;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author zhoushengyuan
 * @since 2018-12-24
 */
public interface ColumnService extends IService<Column> {

    List<Column> groupingByList(String columnId);
}
