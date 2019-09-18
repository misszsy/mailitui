package com.zhou.busi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhou.busi.entity.SysDict;
import com.zhou.busi.mapper.SysDictMapper;
import com.zhou.busi.service.SysDictService;
import com.zhou.framework.config.GlobalConsts;
import com.zhou.framework.utils.JedisUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zhoushengyuan
 * @since 2018-11-17
 */
@Service
public class SysDictServiceImpl extends ServiceImpl<SysDictMapper, SysDict> implements SysDictService {

    /**
     * 字典分类管理
     *
     * @return
     */
    @Override
    public List<SysDict> groupingByList(String type) {
        Map<String, List<SysDict>> dictMap = (Map<String, List<SysDict>>) JedisUtils.getObject(GlobalConsts.CACHE_DICT_MAP);
        if (MapUtils.isEmpty(dictMap)) {
            List<SysDict> dictList = super.list(new QueryWrapper<>());

            dictMap = dictList.stream().collect(Collectors.groupingBy(SysDict::getType));

            JedisUtils.setObject(GlobalConsts.CACHE_DICT_MAP, dictMap, 0);
        }
        List<SysDict> dictList = dictMap.get(type);
        if (CollectionUtils.isEmpty(dictList)) {
            dictList = new ArrayList<>();
        }
        return dictList;
    }
}
