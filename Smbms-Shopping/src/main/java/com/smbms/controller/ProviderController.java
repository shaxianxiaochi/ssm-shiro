package com.smbms.controller;

import com.smbms.pojo.Page;
import com.smbms.pojo.Provider;
import com.smbms.pojo.User;
import com.smbms.service.BillService;
import com.smbms.service.ProviderService;
import com.smbms.tools.CurStatus;
import com.smbms.vo.JSONData;
import com.sun.xml.internal.ws.resources.HttpserverMessages;
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
@RequestMapping("/provider")
public class ProviderController {

    @Autowired
    private ProviderService providerService;
    @Autowired
    private BillService billService;
    private JSONData jsonData = new JSONData();

    @RequestMapping(value = "/getProvider.action")
    @ResponseBody
    public JSONData getProviderInfo(){
        List<Provider> providerList = providerService.getProviderListByObj(null);
        if (providerList != null){
            jsonData.setStatus("200");
            jsonData.setMessage("成功");
            jsonData.setData(providerList);
            return jsonData;
        } else {
            jsonData.setStatus(String.valueOf(CurStatus.ERROR));
            jsonData.setMessage("发生错误");
            return jsonData;
        }
    }

    @RequestMapping(value = "/getProviderByLike.action")
    @ResponseBody
    public JSONData getProviderInfoByLike(@RequestParam(value = "proCode") String proCode,
                                          @RequestParam(value = "proName") String proName,
                                          @RequestParam("pageSize") Integer pageSize,
                                          @RequestParam("currPageNo") Integer currPageNo){
        Map<String,Object> map=new HashMap<>();
        map.put("proCode",proCode);
        map.put("proName",proName);

        Page page=new Page();
        if (pageSize == null) {
            page.setPageSize(5);
        } else {
            page.setPageSize(pageSize);
        }
        page.setTotalCount(providerService.getProviderCount(map));
        map.put("pageSize", pageSize);
        if (currPageNo <= 0) {
            page.setCurrPageNo(1);
        } else {
            page.setCurrPageNo(currPageNo);
        }
        map.put("currPageNo", (page.getCurrPageNo() - 1) * page.getPageSize());
        page.setProviderList(providerService.getProviderPageByMap(map));
        if (page.getProviderList() != null){
            jsonData.setStatus("200");
            jsonData.setMessage("成功");
            jsonData.setData(page);
            return jsonData;
        } else {
            jsonData.setStatus(String.valueOf(CurStatus.ERROR));
            jsonData.setMessage("发生错误");
            return jsonData;
        }
    }

    @RequestMapping(value = "/checkProCode.action")
    @ResponseBody
    public JSONData checkProCode(@RequestParam("proCode") String proCode){
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("proCode",proCode);
        if (providerService.checkByMap(paramMap) == null){
            jsonData.setStatus("200");
            jsonData.setMessage("编码可以使用！");
            System.out.println("编码可以使用！");
        } else {
            jsonData.setStatus(String.valueOf(CurStatus.ERROR ));
            jsonData.setMessage("编码已被占用！");
            System.out.println("编码已被占用！");
        }

        return jsonData;
    }

    @RequestMapping(value = "/addProvider.action")
    @ResponseBody
    public JSONData addProvider(@RequestParam(value = "proCode") String proCode,
                                @RequestParam(value = "proName") String proName,
                                @RequestParam(value = "proDesc") String proDesc,
                                @RequestParam(value = "proContact") String proContact,
                                @RequestParam(value = "proPhone") String proPhone,
                                @RequestParam(value = "proAddress") String proAddress,
                                @RequestParam(value = "proFax") String proFax,
                                HttpSession session
                                ){
        User user = (User) session.getAttribute("userSession");
        Provider provider = new Provider();
        provider.setId(0);
        provider.setProCode(proCode);
        provider.setProName(proName);
        provider.setProDesc(proDesc);
        provider.setProContact(proContact);
        provider.setProPhone(proPhone);
        provider.setProAddress(proAddress);
        provider.setProFax(proFax);
        provider.setCreationDate(new Date());
        provider.setCreatedBy(user.getId());
        if (providerService.add(provider) == 1){
            jsonData.setStatus("200");
            jsonData.setMessage("供应商添加成功！");
        } else {
            jsonData.setStatus(String.valueOf(CurStatus.ERROR));
            jsonData.setMessage("供应商添加失败！");
        }
        return jsonData;
    }

    @RequestMapping(value = "/getProviderById.action")
    @ResponseBody
    public JSONData getProviderById(@RequestParam(value = "id") Integer id){
        Provider provider = providerService.getProviderById(id);
        if (provider != null){
            jsonData.setStatus("200");
            jsonData.setMessage("查询成功！");
            jsonData.setData(provider);
        } else {
            jsonData.setStatus(String.valueOf(CurStatus.ERROR));
            jsonData.setMessage("查询失败！");
        }
        return jsonData;
    }

    @RequestMapping(value = "/updateProvider.action")
    @ResponseBody
    public JSONData updateProvider(@RequestParam(value = "proCode") String proCode,
                                   @RequestParam(value = "id") Integer id,
                                   @RequestParam(value = "proName") String proName,
                                   @RequestParam(value = "proContact") String proContact,
                                   @RequestParam(value = "proPhone") String proPhone,
                                   @RequestParam(value = "proAddress") String proAddress,
                                   @RequestParam(value = "proFax") String proFax,
                                   @RequestParam(value = "proDesc") String proDesc,
                                   HttpSession session){
        User user = (User) session.getAttribute("userSession");
        Provider provider = new Provider();
        provider.setId(id);
        provider.setProCode(proCode);
        provider.setProName(proName);
        provider.setProDesc(proDesc);
        provider.setProContact(proContact);
        provider.setProPhone(proPhone);
        provider.setProAddress(proAddress);
        provider.setProFax(proFax);
        provider.setModifyBy(user.getId());
        provider.setModifyDate(new Date());
        if (providerService.update(provider) == 1){
            jsonData.setStatus("200");
            jsonData.setMessage("更新成功");
        } else {
            jsonData.setStatus(String.valueOf(CurStatus.ERROR));
            jsonData.setMessage("更新失败");
        }
        return jsonData;
    }

    @RequestMapping(value = "/deleteProvider.action")
    @ResponseBody
    public JSONData deleteProviderById(@RequestParam(value = "id") Integer id){
        jsonData.setData(null);
        Map<String,Object> billMap = new HashMap<>();
        billMap.put("providerId",id);
        int billCount = billService.getBillCount(billMap);
        if (billCount > 0){
            jsonData.setStatus(String.valueOf(CurStatus.OBJ_EXIST));
            jsonData.setMessage("此供应商在订单表还有数据");
        } else {
            Map<String,Object> proMap = new HashMap<>();
            proMap.put("id",id);
            if (providerService.getProviderCount(proMap) > 0){
                if (providerService.deleteProviderById(id) == 1){
                    jsonData.setStatus("200");
                    jsonData.setMessage("删除成功");
                } else {
                    jsonData.setStatus(String.valueOf(CurStatus.ERROR));
                    jsonData.setMessage("谁知道发生了啥");
                }
            } else {
                jsonData.setStatus(String.valueOf(CurStatus.DATA_ZERO));
                jsonData.setMessage("没有这个供应商");
            }
        }
        return jsonData;
    }
}
