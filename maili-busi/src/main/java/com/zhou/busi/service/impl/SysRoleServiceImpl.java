package com.zhou.busi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhou.busi.common.utils.UserUtils;
import com.zhou.busi.entity.SysRole;
import com.zhou.busi.entity.SysRoleMenu;
import com.zhou.busi.mapper.SysRoleMapper;
import com.zhou.busi.service.SysRoleMenuService;
import com.zhou.busi.service.SysRoleService;
import com.zhou.framework.utils.StringUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zhoushengyuan
 * @since 2018-11-13
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    @Transactional(readOnly = true)
    @Override
    public String getAdmin(String userId) {
        return baseMapper.getAdmin(userId);
    }


    @Transactional
    @Override
    public boolean save(SysRole sysRole) {
        super.save(sysRole);

        List<SysRoleMenu> sysRoleMenuList = formmatSysRoleMenuList(sysRole.getMenuList(), sysRole.getId());

        if (CollectionUtils.isNotEmpty(sysRoleMenuList)) {
            sysRoleMenuService.saveBatch(sysRoleMenuList);
        }
        UserUtils.removeCache(UserUtils.CACHE_ROLE_LIST);

        return true;
    }


    /**
     * 更新角色
     *
     * @param sysRole
     */
    @Transactional
    @Override
    public boolean updateById(SysRole sysRole) {
        baseMapper.updateById(sysRole);

        List<SysRoleMenu> sysRoleMenuList = formmatSysRoleMenuList(sysRole.getMenuList(), sysRole.getId());

        if (CollectionUtils.isNotEmpty(sysRoleMenuList)) {

            sysRoleMenuService.remove(new QueryWrapper<SysRoleMenu>()
                    .lambda()
                    .eq(SysRoleMenu::getRoleId, sysRole.getId()));

            sysRoleMenuService.saveBatch(sysRoleMenuList);
        }
        UserUtils.removeCache(UserUtils.CACHE_ROLE_LIST);
        return true;
    }


    /**
     * 获取角色用户的菜单集合
     *
     * @param menuList
     * @param roleId
     * @return
     */
    private List<SysRoleMenu> formmatSysRoleMenuList(List<String> menuList, String roleId) {
        List<SysRoleMenu> sysRoleMenuList = new ArrayList<>();

        if (menuList != null && !menuList.isEmpty()) {
            for (String MenuId : menuList) {
                //角色菜单关系
                if (StringUtils.isNotEmpty(MenuId)) {
                    SysRoleMenu sysRoleMenu = new SysRoleMenu();
                    sysRoleMenu.setRoleId(roleId);
                    sysRoleMenu.setMenuId(MenuId);
                    sysRoleMenuList.add(sysRoleMenu);
                }
            }
        }
        return sysRoleMenuList;
    }
}
