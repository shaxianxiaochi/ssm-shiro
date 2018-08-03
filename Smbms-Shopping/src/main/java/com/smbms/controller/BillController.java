package com.smbms.controller;

import com.alibaba.fastjson.JSON;
import com.smbms.pojo.Bill;
import com.smbms.pojo.Page;
import com.smbms.pojo.User;
import com.smbms.service.BillService;
import com.smbms.service.impl.BillServiceImpl;
import com.smbms.tools.CurStatus;
import com.smbms.vo.JSONData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/bill")
public class BillController {

    @Autowired
    private BillService billService;

    private JSONData jsonData = new JSONData();

    @RequestMapping(value = "/getList")
    @ResponseBody
    public JSONData getList(@RequestParam("billName") String billName,
                            @RequestParam("providerId") Integer providerId,
                            @RequestParam("isPayment") Integer isPayment,
                            @RequestParam("pageSize") Integer pageSize,
                            @RequestParam("currPageNo") Integer currPageNo){
        Map<String, Object> billMap = new HashMap<>();
        billMap.put("productName", billName);
        billMap.put("providerId", providerId);
        billMap.put("isPayment", isPayment);
        Page page = new Page();
        if (pageSize == null) {
            page.setPageSize(5);
        } else {
            page.setPageSize(pageSize);
        }
        page.setTotalCount(billService.getBillCount(billMap));
        billMap.put("pageSize", pageSize);
        if (currPageNo <= 0) {
            page.setCurrPageNo(1);
        } else {
            page.setCurrPageNo(currPageNo);
        }
        billMap.put("currPageNo", (page.getCurrPageNo() - 1) * page.getPageSize());
        page.setBillList(billService.getBillPageByMap(billMap));
        if (page.getBillList() != null) {
            jsonData.setStatus("200");
            jsonData.setMessage("查询成功！");
            jsonData.setData(page);
        } else {
            jsonData.setStatus(String.valueOf(CurStatus.ERROR));
            jsonData.setMessage("查询失败！");
        }
        return jsonData;
    }

    @RequestMapping(value = "/getBill")
    @ResponseBody
    public JSONData getBillInfo(@RequestParam(value = "id") Integer id){
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("id",id);
        Bill bill = billService.getBillByMap(paramMap);
        if (bill != null){
            jsonData.setStatus("200");
            jsonData.setMessage("成功");
            jsonData.setData(bill);
            return jsonData;
        } else {
            jsonData.setStatus(String.valueOf(CurStatus.ERROR));
            jsonData.setMessage("查询时出现未知错误");
            return jsonData;
        }
    }

    @RequestMapping(value = "/getBillByBillCode.action")
    @ResponseBody
    public JSONData getBillInfoByBillCode(@RequestParam(value = "billCode") String billCode){
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("billCode",billCode);
        Bill bill = billService.getBillByMap(paramMap);
        if (bill == null){
            jsonData.setStatus("200");
            jsonData.setMessage("成功");
            return jsonData;
        } else {
            jsonData.setStatus(String.valueOf(CurStatus.ERROR));
            jsonData.setMessage("查询时出现未知错误");
            return jsonData;
        }
    }



    @RequestMapping(value = "/updateBill")
    @ResponseBody
    public JSONData updateBill(@RequestParam(value = "billCode") String billCode,
                               @RequestParam(value = "productName") String productName,
                               @RequestParam(value = "productUnit") String productUnit,
                               @RequestParam(value = "productCount") String productCount,
                               @RequestParam(value = "totalPrice") String totalPrice,
                               @RequestParam(value = "providerId") String providerId,
                               @RequestParam(value = "isPayment") String isPayment,
                               @RequestParam(value = "id") String id,
                               HttpSession session
                               ){
        User userSession = (User) session.getAttribute("userSession");
        Bill bill = new Bill();
        bill.setId(Integer.parseInt(id));
        bill.setBillCode(billCode);
        bill.setProductName(productName);
        bill.setProductUnit(productUnit);
        bill.setProductCount(Double.parseDouble(productCount));
        bill.setTotalPrice(Double.parseDouble(totalPrice));
        bill.setProviderId(Integer.parseInt(providerId));
        bill.setIsPayment(Integer.parseInt(isPayment));
        bill.setModifyDate(new Date());
        bill.setModifyBy(userSession.getId());
        if (billService.update(bill) == 1){
            jsonData.setStatus("200");
            jsonData.setMessage("更新成功");
            return jsonData;
        } else {
            jsonData.setStatus(String.valueOf(CurStatus.ERROR));
            jsonData.setMessage("查询时出现未知错误");
            return jsonData;
        }
    }

    @RequestMapping(value = "/delete.action")
    @ResponseBody
    public JSONData deleteBillById(@RequestParam(value = "id") String id){
        if (billService.deleteBillById(Integer.parseInt(id)) == 1){
            jsonData.setStatus("200");
            jsonData.setMessage("删除成功！");
            jsonData.setData(null);
            return jsonData;
        } else {
            jsonData.setStatus(String.valueOf(CurStatus.ERROR));
            jsonData.setMessage("删除失败！");
            jsonData.setData(null);
            return jsonData;
        }
    }

    @RequestMapping(value = "/addBill.action")
    @ResponseBody
    public JSONData addBill(@RequestParam(value = "billCode") String billCode,
                            @RequestParam(value = "productName") String productName,
                            @RequestParam(value = "productUnit") String productUnit,
                            @RequestParam(value = "productCount") double productCount,
                            @RequestParam(value = "totalPrice") double totalPrice,
                            @RequestParam(value = "providerId") Integer providerId,
                            @RequestParam(value = "isPayment") Integer isPayment,
                            HttpSession session){
        User userSession = (User) session.getAttribute("userSession");
        Bill bill = new Bill();
        bill.setId(0);
        bill.setBillCode(billCode);
        bill.setProductName(productName);
        bill.setProductUnit(productUnit);
        bill.setProductCount(productCount);
        bill.setTotalPrice(totalPrice);
        bill.setProviderId(providerId);
        bill.setIsPayment(isPayment);
        bill.setCreatedBy(userSession.getId());
        bill.setCreationDate(new Date());
        if (billService.add(bill) == 1){
            jsonData.setStatus("200");
            jsonData.setMessage("添加成功！");
            return jsonData;
        } else {
            jsonData.setStatus(String.valueOf(CurStatus.ERROR));
            jsonData.setMessage("添加失败！");
            return jsonData;
        }
    }
}
