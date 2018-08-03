package com.smbms.mapper;

import com.smbms.pojo.Address;

import java.util.List;
import java.util.Map;

/**
 * @Desc: 接口
 * @Author: noking
 */
public interface AddressMapper {

    /**
     * 增加
     * @param address
     * @return
     */
    int add(Address address);

    /**
     * 根据id删除
     * @param id
     * @return
     */
    int deleteAddressById(Integer id);

    /**
     * 更新信息
     * @param address
     * @return
     */
    int update(Address address);

    /**
     * 获取总数量
     * @param map
     * @return
     */
    int getAddressCount(Map<String, Object> map);

    /**
     * 根据id获取信息
     * @param id
     * @return
     */
    Address getAddressById(Integer id);

    /**
     * 根据条件获取
     * @param map 多条件
     * @return
     */
    Address getAddressByMap(Map<String, Object> map);

    /**
     * 根据条件获取列表
     * @param address
     * @return
     */
    List<Address> getAddressListByObj(Address address);

    /**
     * 根据条件获取分页
     * @param map
     * @return
     */
    List<Address> getAddressPageByMap(Map<String, Object> map);

}
