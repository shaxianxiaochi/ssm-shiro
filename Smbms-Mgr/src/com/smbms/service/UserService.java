package com.smbms.service;

import com.smbms.pojo.User;

import java.util.List;

public interface UserService {
    /**
     * 根据对象条件查询用户信息
     * @param user
     * @return
     */
    List<User> getUserListByObj(User user);
}
