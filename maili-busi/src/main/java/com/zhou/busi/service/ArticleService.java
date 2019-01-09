package com.zhou.busi.service;

import com.zhou.busi.entity.Article;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhoushengyuan
 * @since 2018-12-24
 */
public interface ArticleService extends IService<Article> {

    Map<String,List<Article>> getArticleIndexMap();

    String selectPrevious(@Param("id") String id, @Param("typeId") String typeId);

    String selectNext(@Param("id") String id,@Param("typeId") String typeId);
}
