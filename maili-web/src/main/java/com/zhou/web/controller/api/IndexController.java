package com.zhou.web.controller.api;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhou.busi.entity.Article;
import com.zhou.busi.service.ArticleService;
import com.zhou.framework.resp.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
@RequestMapping(value = "/api/v1")
@Api(value = "IndexController",description = "首页文章api")
public class IndexController {

    @Autowired
    private ArticleService articleService;

    /**
     * 获取公司新闻的公司新闻和新闻报道
     * @return
     */
    @GetMapping(value = "getNewsList")
    @ApiOperation(value = "新闻列表",notes = "获取公司新闻的公司新闻和新闻报道列表",response = Article.class,httpMethod = "GET")
    @ApiResponses({ @ApiResponse(code = 200, message = "操作成功"),
                    @ApiResponse(code = 400, message = "请求错误")})
    public R getNewsList(){
        return R.ok(articleService.getArticleIndexMap());
    }


    /**
     * 获取成功案例列表
     * @return
     */
    @GetMapping(value = "getCaseList")
    @ApiOperation(value = "案例列表",notes = "获取成功案例列表",response = Article.class,responseContainer = "List",httpMethod = "GET")
    @ApiResponses({ @ApiResponse(code = 200, message = "操作成功"),
                    @ApiResponse(code = 400, message = "请求错误")})
    public R getCaseList(){

     List<Article> articleList=articleService.list(new QueryWrapper<Article>().lambda()
                                .eq(Article::getDisabled,"0")
                                .eq(Article::getStatus,"1")
                                .eq(Article::getColumnId,"4")
                                .orderByDesc(Article::getPublishDate)
                                .last("limit 6"));
        return R.ok(articleList);
    }
}
