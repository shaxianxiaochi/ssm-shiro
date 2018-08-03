package com.smbms.mapper;

import com.smbms.pojo.User;

import java.util.List;
import java.util.Map;

/**
 * @Desc: 接口
 * @Author: noking
 */
public interface UserMapper {

    /**
     * 增加
     * @param user
     * @return
     */
    int add(User user);

    /**
     * 根据id删除
     * @param id
     * @return
     */
    int deleteUserById(Integer id);

    /**
     * 更新信息
     * @param user
     * @return
     */
    int update(User user);

    /**
     * 获取总数量
     * @param map
     * @return
     */
    int getUserCount(Map<String, Object> map);

    /**
     * 根据id获取信息
     * @param id
     * @return
     */
    User getUserById(Integer id);

    /**
     * 根据条件获取
     * @param map 多条件
     * @return
     */
    User getUserByMap(Map<String, Object> map);

    /**
     * 根据条件获取列表
     * @param user
     * @return
     */
    List<User> getUserListByObj(User user);

    /**
     * 根据条件获取分页
     * @param map
     * @return
     */
    List<User> getUserPageByMap(Map<String, Object> map);

}
