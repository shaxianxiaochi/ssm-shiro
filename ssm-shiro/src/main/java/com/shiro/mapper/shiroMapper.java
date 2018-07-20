package com.shiro.mapper;


import com.shiro.pojo.Permission;
import com.shiro.pojo.User;
import com.shiro.pojo.UserData;

import java.util.List;

public interface shiroMapper {


    /**
     * 根据账号获取账号密码
     * @param username
     * @return
     */
   User getUserByUserName(String username);

    /**
     * 根据角色id获取该账号的权限
     * @param roleId
     * @return
     */
   List<Permission> getPermissionsByRoleId(int roleId);


    /**
     * 根据userid获取角色id
     * @param id
     * @return
     */
   List<Integer> getUserRoleByUserId(int id);

    /**
     * 登陆完成后查询全部数据
     * @return
     */
    List<UserData> getAll();
}
