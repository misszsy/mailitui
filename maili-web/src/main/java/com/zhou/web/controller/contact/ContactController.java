package com.zhou.web.controller.contact;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhou.framework.resp.R;
import com.zhou.framework.annotation.Log;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;
import com.zhou.busi.entity.Contact;
import com.zhou.busi.service.ContactService;

import org.springframework.stereotype.Controller;
import com.zhou.busi.common.controller.BaseController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * <p>
 * 预约 前端控制器
 * </p>
 *
 * @author zhoushengyuan
 * @since 2019-01-04
 */
@ApiIgnore
@Controller
@RequestMapping("/contact")
public class ContactController extends BaseController<ContactService, Contact> {


    public String getViewPath() {
        return "contact/";
    }


    /**
     * 页面跳转
     */
    @GetMapping("list")
    @RequiresPermissions("sys:contact:view")
    public String listView() {
        return getViewPath() + "list";
    }

    /**
     * 分页查询列表
     *
     * @param contact
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping(value = {"listData"})
    public @ResponseBody
    R listData(Contact contact, Integer pageNum, Integer pageSize) {
        QueryWrapper wrapper = new QueryWrapper<Contact>();
        return super.listData(wrapper, pageNum, pageSize);
    }

    /**
     * 新增
     *
     * @param contact
     * @return
     */
    @Log(value = "新增")
    @PostMapping("save")
    @RequiresPermissions("sys:contact:save")
    public @ResponseBody
    R save(Contact contact) {
        beanValidator(contact);
        return super.save(contact);
    }

    /**
     * 更新
     *
     * @return
     */
    @Log(value = "更新")
    @PostMapping("update")
    @RequiresPermissions("sys:contact:update")
    public @ResponseBody
    R update(Contact contact) {
        beanValidator(contact);
        return super.update(contact);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @Log(value = "删除")
    @PostMapping("remove/{id}")
    @RequiresPermissions("sys:contact:remove")
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
    @RequiresPermissions("sys:contact:update")
    public @ResponseBody
    R get(@PathVariable String id) {
        return super.get(id);
    }
}
