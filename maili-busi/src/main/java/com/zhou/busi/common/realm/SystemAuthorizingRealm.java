package com.zhou.busi.common.realm;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhou.busi.common.utils.UserUtils;
import com.zhou.busi.entity.SysRole;
import com.zhou.busi.entity.SysUser;
import com.zhou.busi.service.SysUserService;
import com.zhou.framework.config.ConfigConsts;
import com.zhou.framework.security.CustomCredentialsMatcher;
import com.zhou.framework.security.Principal;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;


@Component
public class SystemAuthorizingRealm extends AuthorizingRealm {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SysUserService sysUserService;

    /**
     * 认证回调函数，登陆时调用
     *
     * @param authToken
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo
    doGetAuthenticationInfo(AuthenticationToken authToken) throws AuthenticationException {
        //基于用户名和密码的令牌
        UsernamePasswordToken token = (UsernamePasswordToken) authToken;
        //令牌中可以取出用户名
        String username = token.getUsername();

        // 校验用户名密码
        SysUser user = sysUserService.getOne(new QueryWrapper<SysUser>()
                .lambda().eq(SysUser::getUsername, username));

        if (user != null) {
            //判断是否允许登陆
            if (ConfigConsts.FALSE.equals("1")) {
                throw new AuthenticationException("该帐号未激活,不允许登录。");
            }
            //创建身份信息
            Principal principal = new Principal(user.getId(), user.getUsername(), user.getName());
            //已加密的密码
            String credentials = user.getPassword();
            return new SimpleAuthenticationInfo(principal, credentials, getName());
        }

        return null;
    }

    /**
     * 获取权限授权信息，如果缓存中存在，则直接从缓存中获取，否则就重新获取， 登录成功后调用
     */
    protected AuthorizationInfo getAuthorizationInfo(PrincipalCollection principals) {
        if (principals == null) {
            return null;
        }

        AuthorizationInfo info = null;

        info = (AuthorizationInfo) UserUtils.getCache(UserUtils.CACHE_AUTH_INFO);

        if (info == null) {
            info = doGetAuthorizationInfo(principals);
            if (info != null) {
                UserUtils.putCache(UserUtils.CACHE_AUTH_INFO, info);
            }
        }

        return info;
    }


    /**
     * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        Principal principal = (Principal) getAvailablePrincipal(principals);

        // 校验用户名密码
        SysUser user = UserUtils.get(principal.getId());

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        if (UserUtils.isAdmin(user.getId())) {
            info.addRole("*");
            info.addStringPermission("*:*:*");
        } else {
            List<String> menuList = UserUtils.getPermissionMenuList(user.getId());

            if (CollectionUtils.isNotEmpty(menuList)) {
                info.addStringPermissions(menuList);
            }

            for (SysRole role : user.getRoleList()) {
                info.addRole(role.getCode());
            }
        }
        info.addStringPermission("user");
        return info;
    }

    /**
     * 设定Password校验.
     */
    @PostConstruct
    public void initCredentialsMatcher() {
        //该句作用是重写shiro的密码验证，让shiro用我自己的验证
        setCredentialsMatcher(new CustomCredentialsMatcher());
    }
}