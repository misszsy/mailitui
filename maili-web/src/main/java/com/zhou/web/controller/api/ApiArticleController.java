package com.zhou.web.controller.api;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhou.busi.entity.Article;
import com.zhou.busi.service.ArticleService;
import com.zhou.framework.config.GlobalConsts;
import com.zhou.framework.resp.R;
import com.zhou.framework.utils.StringUtils;
import io.swagger.annotations.*;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhoushengyuan
 * @since 2018-12-24
 */
@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
@Api(value = "ApiArticleController",description = "文章相关api")
public class ApiArticleController{


    @Autowired
    private ArticleService articleService;


    /**
     * 根据文章类型获取新闻列表
     * @param typeId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping(value = "article/{typeId}/{pageNum}/{pageSize}/getNewsList")
    @ApiOperation(value = "新闻列表",notes = "根据文章类型获取新闻列表",response = Article.class,httpMethod = "GET")
    @ApiResponses({ @ApiResponse(code = 200, message = "操作成功"),
            @ApiResponse(code = 400, message = "请求错误")})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "typeId", value = "文章类型",required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "pageNum", value = "当前页码",required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "显示条数",required = true, paramType = "query", dataType = "int")
    })
    public R getNewsList(@PathVariable String typeId,@PathVariable Integer pageNum,@PathVariable Integer pageSize){
        Article article=new Article();
        article.setStatus(GlobalConsts.TURE);
        article.setColumnId("3");
        article.setTypeId(typeId);
        IPage page= articleService.pageMaps(new Page<>(pageNum,pageSize),new QueryWrapper<>(article));
        return R.ok(page);
    }


    /**
     * 根据栏目id获取文章列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping(value = "article/{columnId}/{pageNum}/{pageSize}/list")
    @ApiOperation(value = "文章列表",notes = "根据栏目id获取文章列表",response = Article.class,httpMethod = "GET")
    @ApiResponses({ @ApiResponse(code = 200, message = "操作成功"),
            @ApiResponse(code = 400, message = "请求错误")})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "columnId", value = "栏目id",required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "pageNum", value = "当前页码",required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "显示条数",required = true, paramType = "query", dataType = "int")
    })
    public R list(@PathVariable String columnId,@PathVariable Integer pageNum,@PathVariable Integer pageSize){
        Article article=new Article();
        article.setStatus(GlobalConsts.TURE);
        article.setColumnId(columnId);
        IPage page= articleService.pageMaps(new Page<>(pageNum,pageSize),new QueryWrapper<>(article));
        return R.ok(page);
    }

    /**
     * 根据文章类型获取案例列表
     * @param typeId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping(value = "article/{typeId}/{pageNum}/{pageSize}/getCaseList")
    @ApiOperation(value = "案例列表",notes = "根据文章类型获取案例列表",response = Article.class,httpMethod = "GET")
    @ApiResponses({ @ApiResponse(code = 200, message = "操作成功"),
            @ApiResponse(code = 400, message = "请求错误")})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "typeId", value = "文章类型",required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "pageNum", value = "当前页码",required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "显示条数",required = true, paramType = "query", dataType = "int")
    })
    public R getCaseList(@PathVariable String typeId,@PathVariable Integer pageNum,@PathVariable Integer pageSize){
        Article article=new Article();
        article.setStatus(GlobalConsts.TURE);
        article.setColumnId("4");
        article.setTypeId(typeId);
        IPage page= articleService.pageMaps(new Page<>(pageNum,pageSize),new QueryWrapper<>(article));
        return R.ok(page);
    }


    /**
     * 根据文章id获取文章详情
     * @param id
     * @return
     */
    @GetMapping(value = "article/{id}/get")
    @ApiOperation(value = "文章详情",notes = "根据文章id获取文章详情",response = Map.class,httpMethod = "GET")
    @ApiResponses({ @ApiResponse(code = 200, message = "操作成功"),
            @ApiResponse(code = 400, message = "请求错误")})
    @ApiImplicitParam(name = "id", value = "文章id",required = true, paramType = "query", dataType = "String")
    public R get(@PathVariable String id){
        Map<String,Object> articleMap= articleService.getMap(new QueryWrapper<Article>().setEntity(new Article(id)));
        if(MapUtils.isNotEmpty(articleMap)){
            String articleId=MapUtils.getString(articleMap,"id");
            String typeId=MapUtils.getString(articleMap,"typeId");
            articleMap.put("previous",articleService.selectPrevious(articleId,typeId));
            articleMap.put("next",articleService.selectNext(articleId,typeId));
        }
        return R.ok(articleMap);
    }


    /**
     * 获取新闻资讯的推荐列表
     * @return
     */
    @GetMapping(value = "article/recommendList")
    @ApiOperation(value = "推荐列表",notes = "获取新闻资讯的推荐列表",response = Article.class,httpMethod = "GET")
    @ApiResponses({ @ApiResponse(code = 200, message = "操作成功"),
            @ApiResponse(code = 400, message = "请求错误")})
    public R recommendList(){
        List<Article> recommendList=articleService.list(new QueryWrapper<Article>().lambda()
                                    .eq(Article::getDisabled,"0")
                                    .eq(Article::getStatus,"1")
                                    .eq(Article::getRecommend,"1")
                                    .eq(Article::getColumnId,"3")
                                    .orderByDesc(Article::getPublishDate)
                                    .last("limit 5"));
        return R.ok(recommendList);
    }



    /**
     * 获取相关内容的文章列表
     * @return
     */
    @GetMapping(value = "article/relationList")
    @ApiOperation(value = "相关内容",notes = "获取相关内容的文章列表",response = Article.class,httpMethod = "GET")
    @ApiResponses({ @ApiResponse(code = 200, message = "操作成功"),
            @ApiResponse(code = 400, message = "请求错误")})
    @ApiImplicitParam(name = "keyword", value = "关键字Keyword",required = true, paramType = "query", dataType = "String")
    public R relationList(String keyword){
        List<Article> relationList=new ArrayList<>();
        if(StringUtils.isNotEmpty(keyword)){
            String[] keywords=keyword.split(",");
            relationList=articleService.selectLikeMaps(Arrays.asList(keywords));
        }
        return R.ok(relationList);
    }
}
