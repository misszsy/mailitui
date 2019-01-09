package com.zhou.busi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhou.busi.entity.SysDict;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhoushengyuan
 * @since 2018-11-17
 */
public interface SysDictService extends IService<SysDict> {


    List<SysDict> groupingByList(String type);
}
