package com.zhou.web.controller.link;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhou.framework.resp.R;
import com.zhou.framework.annotation.Log;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;
import com.zhou.busi.entity.Link;
import com.zhou.busi.service.LinkService;

import org.springframework.stereotype.Controller;
import com.zhou.busi.common.controller.BaseController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zhoushengyuan
 * @since 2019-01-21
 */
@ApiIgnore
@Controller
@RequestMapping("/link")
public class LinkController extends BaseController<LinkService, Link> {


    public String getViewPath() {
        return "link/";
    }


    /**
     * 页面跳转
     */
    @GetMapping("list")
    @RequiresPermissions("sys:link:view")
    public String listView() {
        return getViewPath() + "list";
    }

    /**
     * 分页查询列表
     *
     * @param link
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping(value = {"listData"})
    public @ResponseBody
    R listData(Link link, Integer pageNum, Integer pageSize) {
        QueryWrapper wrapper = new QueryWrapper<Link>();
        return super.listData(wrapper, pageNum, pageSize);
    }

    /**
     * 新增
     *
     * @param entity
     * @return
     */
    @Log(value = "新增")
    @PostMapping("save")
    @RequiresPermissions("sys:link:save")
    public @ResponseBody
    R save(Link link) {
        beanValidator(link);
        return super.save(link);
    }

    /**
     * 更新
     *
     * @return
     */
    @Log(value = "更新")
    @PostMapping("update")
    @RequiresPermissions("sys:link:update")
    public @ResponseBody
    R update(Link link) {
        beanValidator(link);
        return super.update(link);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @Log(value = "删除")
    @PostMapping("remove/{id}")
    @RequiresPermissions("sys:link:remove")
    public @ResponseBody
    R remove(@PathVariable String id) {
        return super.remove(id);
    }


    /**
     * 根据id获取
     *
     * @param id
     * @return
     */
    @GetMapping("get/{id}")
    @RequiresPermissions("sys:link:update")
    public @ResponseBody
    R get(@PathVariable String id) {
        return super.get(id);
    }
}
