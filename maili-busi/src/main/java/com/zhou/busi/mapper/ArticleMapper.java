package com.zhou.busi.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhou.busi.entity.Article;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhou.busi.entity.SysLog;
import org.apache.ibatis.annotations.Param;

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
public interface ArticleMapper extends BaseMapper<Article> {

    IPage<Map<String,Object>> selectPageMaps(IPage page,@Param("article") Article article);


    List<Article> getArticleIndexList();


    String selectPrevious(@Param("id") String id,@Param("typeId") String typeId);

    String selectNext(@Param("id") String id,@Param("typeId") String typeId);
}
