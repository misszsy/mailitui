package com.zhou.web.controller.news;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhou.framework.resp.R;
import com.zhou.framework.annotation.Log;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;
import com.zhou.busi.entity.News;
import com.zhou.busi.service.NewsService;

import org.springframework.stereotype.Controller;
import com.zhou.busi.common.controller.BaseController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhoushengyuan
 * @since 2018-12-24
 */
@Controller
@RequestMapping("/news")
public class NewsController extends BaseController<NewsService,News> {


    public String getViewPath() {
        return "news/";
    }


   /**
    *
    * 页面跳转
    */
    @GetMapping("list")
    @RequiresPermissions("sys:news:view")
    public String listView() {
        return getViewPath() + "list";
    }

    /**
    * 分页查询列表
    * @param news
    * @param pageNum
    * @param pageSize
    * @return
    */
    @GetMapping(value = {"listData"})
    public @ResponseBody R listData(News news, Integer pageNum, Integer pageSize) {
        QueryWrapper wrapper=new QueryWrapper<News>();
        return super.listData(wrapper, pageNum, pageSize);
    }

    /**
    * 新增
    * @param entity
    * @return
    */
    @Log(value = "新增")
    @PostMapping("save")
    @RequiresPermissions("sys:news:save")
    public @ResponseBody R save(News news) {
        beanValidator(news);
        return super.save(news);
    }

    /**
    * 更新
    * @return
    */
    @Log(value = "更新")
    @PostMapping("update")
    @RequiresPermissions("sys:news:update")
    public @ResponseBody R update(News news) {
        beanValidator(news);
        return super.update(news);
    }

    /**
    * 删除
    * @param id
    * @return
    */
    @Log(value = "删除")
    @PostMapping("remove/{id}")
    @RequiresPermissions("sys:news:remove")
    public @ResponseBody R remove(@PathVariable String id) {
     return super.remove(id);
    }


    /**
    * 根据id获取
    * @param id
    * @return
    */
    @GetMapping("get/{id}")
    @RequiresPermissions("sys:news:update")
    public @ResponseBody R get(@PathVariable  String id) {
        return super.get(id);
    }
}
