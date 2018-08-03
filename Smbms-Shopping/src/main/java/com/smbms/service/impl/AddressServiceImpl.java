package com.smbms.service.impl;

import com.smbms.pojo.Address;
import com.smbms.mapper.AddressMapper;
import com.smbms.service.AddressService;

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
@Service("addressService")
public class AddressServiceImpl implements AddressService {

    @Autowired(required = false)
    private AddressMapper addressMapper;

    public AddressMapper getAddressMapper() {
        return addressMapper;
    }

    public void setAddressMapper(AddressMapper addressMapper) {
        this.addressMapper = addressMapper;
    }

    @Override
    public int add(Address address) {
        return addressMapper.add(address);
    }

    @Override
    public int deleteAddressById(Integer id) {
        return addressMapper.deleteAddressById(id);
    }

    @Override
    public int update(Address address) {
        return addressMapper.update(address);
    }

    @Override
    public int getAddressCount(Map<String, Object> map) {
        return addressMapper.getAddressCount(map);
    }

    @Override
    public Address getAddressById(Integer id) {
        return addressMapper.getAddressById(id);
    }

    @Override
    public List<Address> getAddressListByObj(Address address) {
        return addressMapper.getAddressListByObj(address);
    }

    @Override
    public List<Address> getAddressPageByMap(Map<String, Object> map) {
        return addressMapper.getAddressPageByMap(map);
    }

    @Override
    public Address getAddressByMap(Map<String, Object> map) {
        return addressMapper.getAddressByMap(map);
    }
}
