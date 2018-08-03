package com.smbms.controller;

import com.smbms.pojo.User;
import com.smbms.service.UserService;
import com.smbms.tools.Base64;
import com.smbms.tools.CurStatus;
import com.smbms.vo.JSONData;
import com.sun.deploy.net.HttpResponse;
import org.apache.ibatis.executor.ReuseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/login")
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login.action")
    @ResponseBody
    public JSONData login(@RequestParam(value = "userCode") String userCode,
                          @RequestParam(value = "userPassword") String userPassword,
                          HttpSession session){
        JSONData jsonData = new JSONData();
        userPassword = Base64.getFromBase64(userPassword);
        Map<String,Object> mapParams = new HashMap<>();
        mapParams.put("userCode",userCode);
        if (userService.getUserByMap(mapParams) == null){
            jsonData.setStatus(String.valueOf(CurStatus.USERNAME_ERROR));
            jsonData.setMessage("请检查用户名");
            return jsonData;
        }
        mapParams.put("userPassword",userPassword);
        if (userService.getUserByMap(mapParams) == null){
            jsonData.setStatus(String.valueOf(CurStatus.USERPWD_ERROR));
            jsonData.setMessage("请检查密码");
            return jsonData;
        } else {
            User user = userService.getUserByMap(mapParams);
            if (user.getUserRole() == 1){
                session.setAttribute("userSession",user);
                jsonData.setStatus("200");
                return jsonData;
            } else {
                jsonData.setStatus(String.valueOf(CurStatus.AUTHORITY_ERROR));
                jsonData.setMessage("您不是管理员，权限不够！");
                return jsonData;
            }
        }
    }

    @RequestMapping(value = "/loginOut.action")
    public void userOut(HttpSession session,
                        HttpServletResponse response,
                        HttpServletRequest request) {
        session.removeAttribute("userSession");
        try {
            response.sendRedirect("/login.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
