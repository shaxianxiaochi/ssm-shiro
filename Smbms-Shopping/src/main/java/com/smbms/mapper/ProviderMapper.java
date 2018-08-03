package com.smbms.mapper;

import com.smbms.pojo.Provider;

import java.util.List;
import java.util.Map;

/**
 * @Desc: 接口
 * @Author: noking
 */
public interface ProviderMapper {

    /**
     * 增加
     * @param provider
     * @return
     */
    int add(Provider provider);

    /**
     * 根据id删除
     * @param id
     * @return
     */
    int deleteProviderById(Integer id);

    /**
     * 更新信息
     * @param provider
     * @return
     */
    int update(Provider provider);

    /**
     * 获取总数量
     * @param map
     * @return
     */
    int getProviderCount(Map<String, Object> map);

    /**
     * 根据id获取信息
     * @param id
     * @return
     */
    Provider getProviderById(Integer id);

    /**
     * 根据条件获取
     * @param map 多条件
     * @return
     */
    Provider getProviderByMap(Map<String, Object> map);

    /**
     * 根据条件获取列表
     * @param provider
     * @return
     */
    List<Provider> getProviderListByObj(Provider provider);

    /**
     * 根据条件获取分页
     * @param map
     * @return
     */
    List<Provider> getProviderPageByMap(Map<String, Object> map);

    /**
     *
     * @param map
     * @return
     */
    Provider checkByMap(Map<String, Object> map);

}
