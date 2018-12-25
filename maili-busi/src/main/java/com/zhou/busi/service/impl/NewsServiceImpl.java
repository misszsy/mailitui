package com.zhou.busi.service.impl;

import com.zhou.busi.entity.News;
import com.zhou.busi.mapper.NewsMapper;
import com.zhou.busi.service.NewsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  新闻服务实现类
 * </p>
 *
 * @author zhoushengyuan
 * @since 2018-12-24
 */
@Service
public class NewsServiceImpl extends ServiceImpl<NewsMapper, News> implements NewsService {

}
