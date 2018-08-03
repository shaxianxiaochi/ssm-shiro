package com.smbms.service.impl;

import com.smbms.pojo.Bill;
import com.smbms.mapper.BillMapper;
import com.smbms.service.BillService;

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
@Service("billService")
public class BillServiceImpl implements BillService {

    @Autowired(required = false)
    private BillMapper billMapper;

    public BillMapper getBillMapper() {
        return billMapper;
    }

    public void setBillMapper(BillMapper billMapper) {
        this.billMapper = billMapper;
    }

    @Override
    public int add(Bill bill) {
        return billMapper.add(bill);
    }

    @Override
    public int deleteBillById(Integer id) {
        return billMapper.deleteBillById(id);
    }

    @Override
    public int update(Bill bill) {
        return billMapper.update(bill);
    }

    @Override
    public int getBillCount(Map<String, Object> map) {
        return billMapper.getBillCount(map);
    }

    @Override
    public Bill getBillById(Integer id) {
        return billMapper.getBillById(id);
    }

    @Override
    public List<Bill> getBillListByObj(Bill bill) {
        return billMapper.getBillListByObj(bill);
    }

    @Override
    public List<Bill> getBillPageByMap(Map<String, Object> map) {
        return billMapper.getBillPageByMap(map);
    }

    @Override
    public Bill getBillByMap(Map<String, Object> map) {
        return billMapper.getBillByMap(map);
    }
}
