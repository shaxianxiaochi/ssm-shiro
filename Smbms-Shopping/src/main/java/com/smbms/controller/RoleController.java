package com.smbms.controller;

import com.smbms.pojo.Role;
import com.smbms.service.RoleService;
import com.smbms.tools.CurStatus;
import com.smbms.vo.JSONData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;
    private JSONData jsonData = new JSONData();

    @RequestMapping(value = "/getRole.action")
    @ResponseBody
    public JSONData getRole(){
        List<Role> roleList = roleService.getRoleListByObj(null);
        if (roleList != null){
            jsonData.setStatus("200");
            jsonData.setMessage("查询成功");
            jsonData.setData(roleList);
        } else {
            jsonData.setStatus(String.valueOf(CurStatus.ERROR));
            jsonData.setMessage("查询失败");
        }
        return jsonData;
    }
}
