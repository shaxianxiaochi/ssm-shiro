package com.empdb.dao;

import com.empdb.pojo.Employee;

import java.util.List;

/**
 * 员工数据接口
 */
public interface EmployeeDao {
    /**
     * 获取员工数量
     * @return
     */
    int getEmployeeCount();

    /**
     * 分页显示员工信息
     * @param pageSize
     * @param currPageNo
     * @return
     */
    List<Employee> getEmployeeList(int pageSize,int currPageNo);
}
