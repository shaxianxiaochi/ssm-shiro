package com.empdb.pojo;

import java.util.List;

public class Page {

    private int totalPageCount;
    private int pageSize;
    private int totalCount;
    private int currPageNo;
    private List<Employee> employeeList;

    public int getTotalPageCount() {
        return totalPageCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        if (pageSize > 0) {
            this.pageSize = pageSize;
        } else {
            this.pageSize = 0;
        }
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        if (totalCount > 0) {
            this.totalCount = totalCount;
            this.totalPageCount = this.totalCount % this.pageSize == 0
                    ? (this.totalCount / this.pageSize) : (this.totalCount / this.pageSize + 1);
        }
    }

    public int getCurrPageNo() {
        return currPageNo;
    }

    public void setCurrPageNo(int currPageNo) {
        if (currPageNo <= 0) {
            this.currPageNo = 1;
        } else if (currPageNo > this.totalPageCount) {
            this.currPageNo = this.totalPageCount;
        } else {
            this.currPageNo = currPageNo;
        }
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }
}
