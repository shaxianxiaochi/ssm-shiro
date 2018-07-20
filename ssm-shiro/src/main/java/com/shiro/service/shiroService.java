package com.shiro.service;

import com.shiro.pojo.Permission;
import com.shiro.pojo.User;
import com.shiro.pojo.UserData;
import org.springframework.stereotype.Component;

import java.util.List;


public interface shiroService {


    /**
     * 根据账号获取账号密码
     * @param username
     * @return
     */
    User getUserByUserName(String username);

    /**
     * 根据账号获取该账号的权限
     * @param user
     * @return
     */
    List<Permission> getPermissionbyUser(User user);

    /**
     * 登陆完成后查询全部数据
     * @return
     */
    List<UserData> getAll();
}
