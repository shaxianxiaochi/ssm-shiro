package com.shiro.shiro;

import com.shiro.pojo.Permission;
import com.shiro.pojo.User;
import com.shiro.service.shiroService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CustomRealm extends AuthorizingRealm {

    @Autowired(required = false)
    private shiroService shiroService;



    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection pc) {
        /**
         * 流程
         * 1.根据用户user ->2,获取角色id ->3,根据角色id获取权限permission
         */
        //方法一：获得user对象
        User user= (User) pc.getPrimaryPrincipal();
        SimpleAuthorizationInfo info =new SimpleAuthorizationInfo();
        //获取permission
        if(user!=null){
            List<Permission> permissionsList=shiroService.getPermissionbyUser(user);
            if (permissionsList.size()!=0){
                for (Permission p:permissionsList){
                    info.addStringPermission(p.getUrl());
                }
                return info;
            }
        }
        return null;
    }


    /**
     * 用于登录认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("======用户登录认证======");
        UsernamePasswordToken token= (UsernamePasswordToken) authenticationToken;
        System.out.println("1:"+token.getUsername());
        User user=shiroService.getUserByUserName(token.getUsername());
        System.out.println("2");
        if (user == null) {
            return null;
        }
        AuthenticationInfo info=new SimpleAuthenticationInfo(user,user.getPassword(),this.getClass().getSimpleName());
        return info;
    }
}
