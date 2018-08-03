package com.smbms.mapper;

import com.smbms.pojo.Bill;

import java.util.List;
import java.util.Map;

/**
 * @Desc: 接口
 * @Author: noking
 */
public interface BillMapper {

    /**
     * 增加
     * @param bill
     * @return
     */
    int add(Bill bill);

    /**
     * 根据id删除
     * @param id
     * @return
     */
    int deleteBillById(Integer id);

    /**
     * 更新信息
     * @param bill
     * @return
     */
    int update(Bill bill);

    /**
     * 获取总数量
     * @param map
     * @return
     */
    int getBillCount(Map<String, Object> map);

    /**
     * 根据id获取信息
     * @param id
     * @return
     */
    Bill getBillById(Integer id);

    /**
     * 根据条件获取
     * @param map 多条件
     * @return
     */
    Bill getBillByMap(Map<String, Object> map);

    /**
     * 根据条件获取列表
     * @param bill
     * @return
     */
    List<Bill> getBillListByObj(Bill bill);

    /**
     * 根据条件获取分页
     * @param map
     * @return
     */
    List<Bill> getBillPageByMap(Map<String, Object> map);

}
