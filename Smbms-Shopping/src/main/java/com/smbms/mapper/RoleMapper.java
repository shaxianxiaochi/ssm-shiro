package com.smbms.mapper;

import com.smbms.pojo.Role;

import java.util.List;
import java.util.Map;

/**
 * @Desc: 接口
 * @Author: noking
 */
public interface RoleMapper {

    /**
     * 增加
     * @param role
     * @return
     */
    int add(Role role);

    /**
     * 根据id删除
     * @param id
     * @return
     */
    int deleteRoleById(Integer id);

    /**
     * 更新信息
     * @param role
     * @return
     */
    int update(Role role);

    /**
     * 获取总数量
     * @param map
     * @return
     */
    int getRoleCount(Map<String, Object> map);

    /**
     * 根据id获取信息
     * @param id
     * @return
     */
    Role getRoleById(Integer id);

    /**
     * 根据条件获取
     * @param map 多条件
     * @return
     */
    Role getRoleByMap(Map<String, Object> map);

    /**
     * 根据条件获取列表
     * @param role
     * @return
     */
    List<Role> getRoleListByObj(Role role);

    /**
     * 根据条件获取分页
     * @param map
     * @return
     */
    List<Role> getRolePageByMap(Map<String, Object> map);

}
