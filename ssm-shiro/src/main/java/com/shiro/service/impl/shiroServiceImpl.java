package com.shiro.service.impl;


import com.shiro.mapper.shiroMapper;
import com.shiro.pojo.Permission;
import com.shiro.pojo.User;
import com.shiro.pojo.UserData;
import com.shiro.service.shiroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("shiroService")
public class shiroServiceImpl implements shiroService {

    @Autowired(required = false)
    private shiroMapper shiroMapper;


    @Override
    public User getUserByUserName(String username) {
        //根据账号获取账号密码
        User user=shiroMapper.getUserByUserName(username);
        return user;
    }

    @Override
    public List<Permission> getPermissionbyUser(User user) {
        //获取到用户角色userRole
        List<Integer> roleId=shiroMapper.getUserRoleByUserId(user.getId());
        List<Permission> permissions=new ArrayList<>();
        if(roleId!=null && roleId.size()!=0){
            //根据roleId 获取permission
            for (Integer i:roleId){
                permissions.addAll(shiroMapper.getPermissionsByRoleId(i));
            }
        }
        System.out.println(permissions);
        return permissions;
    }

    @Override
    public List<UserData> getAll() {
        List<UserData> userDataList=shiroMapper.getAll();
        return userDataList;
    }
}
