package com.zhou.web.controller.${table.entityPath};

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhou.framework.resp.R;
import com.zhou.framework.annotation.Log;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;
import com.zhou.busi.entity.${table.entityName};
import com.zhou.busi.service.${table.serviceName};

<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>

/**
 * <p>
 * ${table.comment!} 前端控制器
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
<#if restControllerStyle>
@RestController
<#else>
@Controller
</#if>
@RequestMapping("<#if package.ModuleName??>/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
<#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass}<${table.serviceName},${table.entityName}> {
<#else>
public class ${table.controllerName} {
</#if>


    public String getViewPath() {
        return "${table.entityPath}/";
    }


   /**
    *
    * 页面跳转
    */
    @GetMapping("list")
    @RequiresPermissions("sys:${table.entityPath}:view")
    public String listView() {
        return getViewPath() + "list";
    }

    /**
    * 分页查询列表
    * @param ${table.entityPath}
    * @param pageNum
    * @param pageSize
    * @return
    */
    @GetMapping(value = {"listData"})
    public @ResponseBody R listData(${table.entityName} ${table.entityPath}, Integer pageNum, Integer pageSize) {
        QueryWrapper wrapper=new QueryWrapper<${table.entityName}>();
        return super.listData(wrapper, pageNum, pageSize);
    }

    /**
    * 新增
    * @param entity
    * @return
    */
    @Log(value = "新增")
    @PostMapping("save")
    @RequiresPermissions("sys:${table.entityPath}:save")
    public @ResponseBody R save(${table.entityName} ${table.entityPath}) {
        beanValidator(${table.entityPath});
        return super.save(${table.entityPath});
    }

    /**
    * 更新
    * @return
    */
    @Log(value = "更新")
    @PostMapping("update")
    @RequiresPermissions("sys:${table.entityPath}:update")
    public @ResponseBody R update(${table.entityName} ${table.entityPath}) {
        beanValidator(${table.entityPath});
        return super.update(${table.entityPath});
    }

    /**
    * 删除
    * @param id
    * @return
    */
    @Log(value = "删除")
    @PostMapping("remove/{id}")
    @RequiresPermissions("sys:${table.entityPath}:remove")
    public @ResponseBody R remove(@PathVariable String id) {
     return super.remove(id);
    }


    /**
    * 根据id获取
    * @param id
    * @return
    */
    @GetMapping("get/{id}")
    @RequiresPermissions("sys:${table.entityPath}:update")
    public @ResponseBody R get(@PathVariable  String id) {
        return super.get(id);
    }
}
</#if>
