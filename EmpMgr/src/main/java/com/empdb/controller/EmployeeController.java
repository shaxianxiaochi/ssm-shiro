package com.empdb.controller;

import com.empdb.dao.EmployeeDao;
import com.empdb.dao.impl.EmployeeDaoImpl;
import com.empdb.pojo.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    private EmployeeDao employeeDao = new EmployeeDaoImpl();

    @RequestMapping(value = "/page.action")
    @ResponseBody
    public Page findEmployeeToPage(@RequestParam("pageSize") int pageSizeParam,
                                   @RequestParam("currPageNo") int currPageNo){
        Page page = new Page();
        page.setPageSize(pageSizeParam);
        page.setTotalCount(employeeDao.getEmployeeCount());
        page.setCurrPageNo(currPageNo);
        int shiftingCount = (page.getCurrPageNo() - 1) * pageSizeParam;
        page.setEmployeeList(employeeDao.getEmployeeList(pageSizeParam,shiftingCount));
        return  page;
    }
}
