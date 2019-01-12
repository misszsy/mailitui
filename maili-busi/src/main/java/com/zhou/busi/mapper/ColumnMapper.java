package com.zhou.busi.mapper;

import com.zhou.busi.entity.Column;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhoushengyuan
 * @since 2018-12-24
 */
public interface ColumnMapper extends BaseMapper<Column> {


    List<Map<String,Object>> selectColumnMaps();


    List<Column> selectChildrenList();
}
