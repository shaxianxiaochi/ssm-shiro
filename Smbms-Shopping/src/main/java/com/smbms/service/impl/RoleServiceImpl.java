package com.smbms.service.impl;

import com.smbms.pojo.Role;
import com.smbms.mapper.RoleMapper;
import com.smbms.service.RoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Desc: 接口实现类
 * @Author: noking
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService {

    @Autowired(required = false)
    private RoleMapper roleMapper;

    public RoleMapper getRoleMapper() {
        return roleMapper;
    }

    public void setRoleMapper(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    @Override
    public int add(Role role) {
        return roleMapper.add(role);
    }

    @Override
    public int deleteRoleById(Integer id) {
        return roleMapper.deleteRoleById(id);
    }

    @Override
    public int update(Role role) {
        return roleMapper.update(role);
    }

    @Override
    public int getRoleCount(Map<String, Object> map) {
        return roleMapper.getRoleCount(map);
    }

    @Override
    public Role getRoleById(Integer id) {
        return roleMapper.getRoleById(id);
    }

    @Override
    public List<Role> getRoleListByObj(Role role) {
        return roleMapper.getRoleListByObj(role);
    }

    @Override
    public List<Role> getRolePageByMap(Map<String, Object> map) {
        return roleMapper.getRolePageByMap(map);
    }

    @Override
    public Role getRoleByMap(Map<String, Object> map) {
        return roleMapper.getRoleByMap(map);
    }
}
