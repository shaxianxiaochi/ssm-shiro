package com.smbms.servlet;

import com.alibaba.fastjson.JSON;
import com.smbms.pojo.User;
import com.smbms.service.UserService;
import com.smbms.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "DoUserServlet",urlPatterns = "/DoUserServlet")
public class DoUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String opr = request.getParameter("opr");
        UserService userService = new UserServiceImpl();
        Map<String,Object> outParams = new HashMap<>();
        if(opr != null || !"".equals(opr)){
            if("getAll".equals(opr)){
                System.out.println("aaaaaaaaaaaaaaaa");
                getAllUser(request,response,out,userService);
            }
        } else {
            outParams.put("status",500);
            out.print(JSON.toJSONString(outParams));
        }

    }

    /**
     * 请求获取所有用户信息
     * @param request
     * @param response
     * @param out
     * @param userService
     */
    private void getAllUser(HttpServletRequest request, HttpServletResponse response, PrintWriter out, UserService userService) {
        List<User> list = userService.getUserListByObj(null);
        Map<String,Object> outParams = new HashMap<>();
        if(list.size() != 0){
            outParams.put("userList",list);
            out.print(JSON.toJSONString(outParams));
        }else{

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
