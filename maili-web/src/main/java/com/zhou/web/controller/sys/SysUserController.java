package com.zhou.web.controller.sys;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhou.busi.common.controller.BaseController;
import com.zhou.busi.common.utils.UserUtils;
import com.zhou.busi.entity.SysUser;
import com.zhou.busi.service.SysUserService;
import com.zhou.framework.annotation.Log;
import com.zhou.framework.resp.R;
import com.zhou.framework.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

/**
 * <p>
 * 系统用户 前端控制器
 * </p>
 *
 * @author zhoushengyuan
 * @since 2018-11-08
 */
@Controller
@RequestMapping("/sys/user")
@ApiIgnore
@Api(value = "SysUserController", description = "系统用户相关api")
public class SysUserController extends BaseController<SysUserService, SysUser> {

    public String getViewPath() {
        return "sys/user/";
    }

    /**
     * 页面跳转
     */
    @GetMapping("list")
    @RequiresPermissions("sys:user:view")
    @ApiIgnore
    public String listView() {
        return getViewPath() + "list";
    }

    /**
     * 查询系统用户列表
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("listData")
    @ApiOperation(value = "用户列表", notes = "查询系统用户列表", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "当前页码", required = true, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "显示条数", required = true, paramType = "query", dataType = "Integer")
    })
    public @ResponseBody
    R listData(SysUser user, Integer pageNum, Integer pageSize) {
        QueryWrapper wrapper = new QueryWrapper<SysUser>()
                .like(StringUtils.isNotEmpty(user.getName()), "name", user.getName())
                .like(StringUtils.isNotEmpty(user.getUsername()), "username", user.getUsername());
        return super.listData(wrapper, pageNum, pageSize);
    }

    /**
     * 新增用户
     *
     * @param user
     * @return
     */
    @Log(value = "新增用户")
    @PostMapping("save")
    @RequiresPermissions("sys:user:save")
    public @ResponseBody
    R save(@RequestBody SysUser user) {
        beanValidator(user);
        if (StringUtils.isEmpty(user.getPassword())) {
            return R.fail("用户密码信息不能为空");
        }
        if (baseService.getOne(new QueryWrapper<SysUser>()
                .lambda()
                .eq(SysUser::getUsername, user.getUsername())) != null) {
            return R.fail("登录账号重复");
        }
        return super.save(user);
    }


    /**
     * 更新用户
     *
     * @return
     */
    @Log(value = "更新用户")
    @PostMapping("update")
    @RequiresPermissions("sys:user:update")
    public @ResponseBody
    R update(@RequestBody SysUser user, String oldUsername) {

        beanValidator(user);
        if (!StringUtils.equals(oldUsername, user.getUsername())) {
            SysUser sysUser = baseService.getOne(new QueryWrapper<SysUser>()
                    .lambda()
                    .eq(SysUser::getUsername, user.getUsername()));
            if (sysUser != null) {
                return R.fail("更新失败,登录账号已经存在");
            }
        }
        return super.update(user);
    }

    /**
     * 删除用户信息
     *
     * @param id
     * @return
     */
    @Log(value = "删除管理")
    @PostMapping(value = {"remove/{id}"})
    @RequiresPermissions("sys:user:remove")
    @ApiOperation(value = "删除用户", notes = "根据用户id删除用户", httpMethod = "POST")
    @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "String")
    public @ResponseBody
    R remove(@PathVariable("id") String id) {
        super.remove(id);
        UserUtils.clearCache(new SysUser(id));
        return R.ok();
    }

    /**
     * Ajax权限检验
     *
     * @return
     */
    @GetMapping("checkPermission")
    @RequiresPermissions(value = {"sys:user:save", "sys:user:update"}, logical = Logical.OR)
    @ApiIgnore
    public @ResponseBody
    R checkPermission() {
        return R.ok();
    }


    /**
     * 根据id获取用户信息
     *
     * @param id
     * @return
     */
    @GetMapping(value = {"get/{id}"})
    @RequiresPermissions("sys:user:update")
    @ApiOperation(value = "获取用户", notes = "根据用户id获取用户信息", httpMethod = "GET")
    @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "String")
    public @ResponseBody
    R get(@PathVariable("id") String id) {
        return R.ok(UserUtils.get(id));
    }
}
