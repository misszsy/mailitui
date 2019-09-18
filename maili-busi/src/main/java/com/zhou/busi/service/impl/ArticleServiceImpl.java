package com.zhou.busi.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhou.busi.entity.Article;
import com.zhou.busi.entity.SysDict;
import com.zhou.busi.entity.SysLog;
import com.zhou.busi.mapper.ArticleMapper;
import com.zhou.busi.service.ArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhou.framework.utils.StringUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 新闻服务实现类
 * </p>
 *
 * @author zhoushengyuan
 * @since 2018-12-24
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Override
    public IPage<Map<String, Object>> pageMaps(IPage<Article> page, Wrapper<Article> queryWrapper) {
        return baseMapper.selectPageMaps(page, queryWrapper.getEntity());
    }


    /**
     * 获取公司新闻和媒体报道
     *
     * @return
     */
    @Override
    public Map<String, List<Article>> getArticleIndexMap() {
        Map<String, List<Article>> mapList = baseMapper.getArticleIndexList()
                .stream().collect(Collectors.groupingBy(Article::getTypeId));
        return mapList;
    }

    /**
     * 根据id查询文章详情上下篇
     *
     * @param queryWrapper
     * @return
     */
    @Override
    public Map<String, Object> getMap(Wrapper<Article> queryWrapper) {
        return baseMapper.getArticleMap(queryWrapper.getEntity().getId());
    }

    /**
     * 多个关键字模糊查询
     *
     * @param keywords
     * @return
     */
    @Override
    public List<Article> selectLikeMaps(List<String> keywords) {
        return baseMapper.selectLikeMaps(keywords);
    }
}
