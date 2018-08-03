package com.smbms.dao;

import com.smbms.pojo.User;

import java.util.List;

/**
 * 用户数据服务接口
 */
public interface UserMapper {
    /**
     * 根据对象条件查询用户信息
     * @param user
     * @return
     */
    List<User> getUserListByObj(User user);
}
