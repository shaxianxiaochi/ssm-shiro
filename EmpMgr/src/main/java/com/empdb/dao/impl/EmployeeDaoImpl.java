package com.empdb.dao.impl;

import com.empdb.dao.EmployeeDao;
import com.empdb.pojo.Employee;
import com.empdb.tools.BaseDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * 员工数据实现类
 */
public class EmployeeDaoImpl extends BaseDao implements EmployeeDao {
    private ResultSet rs;
    private PreparedStatement pstmt;
    private Connection conn;

    @Override
    public int getEmployeeCount() {
        int count = 0;
        String sql = "SELECT COUNT(1) as con FROM employee";
        try {
            conn = getConn();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            rs.next();
            count = rs.getInt("con");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeAll(rs,pstmt,conn);
        }
        return count;
    }

    @Override
    public List<Employee> getEmployeeList(int pageSize, int currPageNo) {
        List<Employee> employeeList = new ArrayList<>();
        String sql = "SELECT *,TIMESTAMPDIFF(year,eBirthday,NOW()) as age FROM employee LIMIT ?,?";
        try {
            conn = getConn();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,currPageNo);
            pstmt.setInt(2,pageSize);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Employee employee = new Employee();
                employee.seteId(rs.getInt("eId"));
                employee.seteName(rs.getString("eName"));
                employee.seteBirthday(rs.getDate("eBirthday"));
                employee.seteAddress(rs.getString("eAddress"));
                employee.seteSex(rs.getString("eSex"));
                employee.setAge(rs.getInt("age"));
                employeeList.add(employee);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeAll(rs,pstmt,conn);
        }
        return employeeList;
    }
}
