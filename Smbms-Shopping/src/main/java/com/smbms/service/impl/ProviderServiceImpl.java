package com.smbms.service.impl;

import com.smbms.pojo.Provider;
import com.smbms.mapper.ProviderMapper;
import com.smbms.service.ProviderService;

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
@Service("providerService")
public class ProviderServiceImpl implements ProviderService {

    @Autowired(required = false)
    private ProviderMapper providerMapper;

    public ProviderMapper getProviderMapper() {
        return providerMapper;
    }

    public void setProviderMapper(ProviderMapper providerMapper) {
        this.providerMapper = providerMapper;
    }

    @Override
    public int add(Provider provider) {
        return providerMapper.add(provider);
    }

    @Override
    public int deleteProviderById(Integer id) {
        return providerMapper.deleteProviderById(id);
    }

    @Override
    public int update(Provider provider) {
        return providerMapper.update(provider);
    }

    @Override
    public int getProviderCount(Map<String, Object> map) {
        return providerMapper.getProviderCount(map);
    }

    @Override
    public Provider getProviderById(Integer id) {
        return providerMapper.getProviderById(id);
    }

    @Override
    public List<Provider> getProviderListByObj(Provider provider) {
        return providerMapper.getProviderListByObj(provider);
    }

    @Override
    public List<Provider> getProviderPageByMap(Map<String, Object> map) {
        return providerMapper.getProviderPageByMap(map);
    }

    @Override
    public Provider checkByMap(Map<String, Object> map) {
        return providerMapper.checkByMap(map);
    }

    @Override
    public Provider getProviderByMap(Map<String, Object> map) {
        return providerMapper.getProviderByMap(map);
    }
}
