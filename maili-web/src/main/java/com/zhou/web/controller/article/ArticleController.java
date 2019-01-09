package com.zhou.web.controller.article;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhou.busi.common.utils.UserUtils;
import com.zhou.busi.entity.Article;
import com.zhou.framework.config.GlobalConsts;
import com.zhou.framework.resp.R;
import com.zhou.framework.annotation.Log;
import com.zhou.framework.utils.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.zhou.busi.service.ArticleService;

import org.springframework.stereotype.Controller;
import com.zhou.busi.common.controller.BaseController;
import springfox.documentation.annotations.ApiIgnore;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhoushengyuan
 * @since 2018-12-24
 */
@ApiIgnore
@Controller
@RequestMapping("/article")
public class ArticleController extends BaseController<ArticleService, Article> {


    public String getViewPath() {
        return "article/";
    }


   /**
    *
    * 页面跳转
    */
    @GetMapping("list")
    @RequiresPermissions("sys:article:view")
    public String listView() {
        return getViewPath() + "list";
    }


    /**
     *
     * add页面跳转
     */
    @GetMapping("add")
    public String addView() {
        return getViewPath() + "add";
    }

    /**
     *
     * edit页面跳转
     */
    @GetMapping("edit/{id}")
    public String editView(@PathVariable String id, Model model) {
        model.addAttribute("id",id);
        return getViewPath() + "edit";
    }

    /**
    * 分页查询列表
    * @param article
    * @param pageNum
    * @param pageSize
    * @return
    */
    @GetMapping(value = {"listData"})
    public @ResponseBody R listData(Article article, Integer pageNum, Integer pageSize) {
        IPage page=baseService.pageMaps(new Page<>(pageNum,pageSize),new QueryWrapper<>(article));
        return R.ok(page);
    }

    /**
    * 新增
    * @param article
    * @return
    */
    @Log(value = "新增")
    @PostMapping("save")
    @RequiresPermissions("sys:article:save")
    public @ResponseBody R save(Article article) {
        beanValidator(article);
        article.setCreateBy(UserUtils.getUser().getId());
        article.setCreateDate(LocalDateTime.now());
        article.setRecommend(GlobalConsts.FALSE);
        article.setStatus(GlobalConsts.FALSE);
        article.setDisabled(GlobalConsts.FALSE);
        return super.save(article);
    }

    /**
    * 更新
    * @return
    */
    @Log(value = "更新")
    @PostMapping("update")
    @RequiresPermissions("sys:article:update")
    public @ResponseBody R update(Article article) {
        beanValidator(article);
        return super.update(article);
    }

    /**
     * 删除
     * @param ids
     * @return
     */
    @Log(value = "删除")
    @PostMapping("remove")
    @RequiresPermissions("sys:article:remove")
    public  @ResponseBody R remove(@RequestParam("ids[]")String[] ids) {
        baseService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }

    /**
    * 根据id获取
    * @param id
    * @return
    */
    @GetMapping("get/{id}")
    @RequiresPermissions("sys:article:update")
    public @ResponseBody R get(@PathVariable  String id) {
        return super.get(id);
    }


    /**
     * 推荐文章
     * @param ids
     * @return
     */
    @Log(value = "推荐文章")
    @PostMapping("advice")
    @RequiresPermissions("sys:article:advice")
    public  @ResponseBody R advice(@RequestParam("ids[]") String[] ids) {
        List<Article> articles=convertArticleList(ids,"advice",GlobalConsts.TURE);
        baseService.updateBatchById(articles);
        return R.ok();
    }

    /**
     * 取消推荐
     * @param ids
     * @return
     */
    @Log(value = "取消推荐")
    @PostMapping("cancel")
    @RequiresPermissions("sys:article:cancel")
    public  @ResponseBody R cancel(@RequestParam("ids[]") String[] ids) {
        List<Article> articles=convertArticleList(ids,"advice",GlobalConsts.FALSE);
        baseService.updateBatchById(articles);
        return R.ok();
    }


    /**
     * 发布文章
     * @param ids
     * @return
     */
    @Log(value = "发布文章")
    @PostMapping("active")
    @RequiresPermissions("sys:article:active")
    public  @ResponseBody R active(@RequestParam("ids[]") String[] ids) {
      List<Article> articles=convertArticleList(ids,"active",GlobalConsts.TURE);
      baseService.updateBatchById(articles);
      return R.ok();
    }


    /**
     * 取消发布
     * @param ids
     * @return
     */
    @Log(value = "取消发布")
    @PostMapping("shutdown")
    @RequiresPermissions("sys:article:shutdown")
    public  @ResponseBody R shutdown(@RequestParam("ids[]") String[] ids) {
        List<Article> articles=convertArticleList(ids,"active",GlobalConsts.CANCEL);
        baseService.updateBatchById(articles);
        return  R.ok();
    }


    private List<Article> convertArticleList(String[] ids,String type,String status){
        List<Article> articles=new ArrayList<>();
        Arrays.stream(ids).forEach(id -> {
            Article article=new Article();
            article.setId(id);
            if(StringUtils.equals(type,"active")){
                article.setStatus(status);
                article.setPublishBy(UserUtils.getUser().getId());
                article.setPublishDate(LocalDateTime.now());
            }else{
                article.setRecommend(status);
            }
            articles.add(article);
        });
        return articles;
    }
}
