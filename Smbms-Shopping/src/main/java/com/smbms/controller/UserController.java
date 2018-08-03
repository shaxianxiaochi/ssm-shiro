package com.smbms.controller;

import com.smbms.pojo.Page;
import com.smbms.pojo.User;
import com.smbms.service.UserService;
import com.smbms.tools.CurStatus;
import com.smbms.vo.JSONData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    private JSONData jsonData = new JSONData();

    @RequestMapping(value = "/getUser.action")
    @ResponseBody
    public JSONData getUser(@RequestParam(value = "userName") String userName,
                            @RequestParam(value = "roleId") Integer roleId,
                            @RequestParam(value = "pageSize") Integer pageSize,
                            @RequestParam(value = "currPageNo") Integer currPageNo) {
        Map<String, Object> userMap = new HashMap<>();
        userMap.put("userName", userName);
        userMap.put("userRole", roleId);
        Page page = new Page();
        if (pageSize == null) {
            page.setPageSize(5);
        } else {
            page.setPageSize(pageSize);
        }
        page.setTotalCount(userService.getUserCount(userMap));
        userMap.put("pageSize", pageSize);
        if (currPageNo <= 0) {
            page.setCurrPageNo(1);
        } else {
            page.setCurrPageNo(currPageNo);
        }
        userMap.put("currPageNo", (page.getCurrPageNo() - 1) * page.getPageSize());
        page.setUserList(userService.getUserPageByMap(userMap));
        if (page.getUserList() != null) {
            jsonData.setStatus("200");
            jsonData.setMessage("查询成功！");
            jsonData.setData(page);
        } else {
            jsonData.setStatus(String.valueOf(CurStatus.ERROR));
            jsonData.setMessage("查询失败！");
        }
        return jsonData;
    }


    @RequestMapping(value = "/getUserByid.action")
    @ResponseBody
    public JSONData getUserById(@RequestParam(value = "id") Integer id) {
        User user = userService.getUserById(id);
        if (user != null) {
            jsonData.setStatus("200");
            jsonData.setMessage("查询成功");
            jsonData.setData(user);
        } else {
            jsonData.setStatus(String.valueOf(CurStatus.ERROR));
            jsonData.setMessage("查询失败");
            jsonData.setData(null);
        }
        return jsonData;
    }

    @RequestMapping(value = "/updateUser.action")
    @ResponseBody
    public JSONData update(@RequestParam(value = "id") Integer id,
                           @RequestParam(value = "userName") String userName,
                           @RequestParam(value = "gender") Integer gender,
                           @RequestParam(value = "birthday") String birthday,
                           @RequestParam(value = "phone") String phone,
                           @RequestParam(value = "userRole") Integer userRole,
                           @RequestParam(value = "address") String address,
                           HttpSession session) {
        SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd" );
        Date birthdayDate = null;
        try {
            birthdayDate = sdf.parse(birthday);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        User userSession = (User) session.getAttribute("userSession");
        User user = new User();
        user.setId(id);
        user.setUserName(userName);
        user.setGender(gender);
        user.setBirthday(birthdayDate);
        user.setPhone(phone);
        user.setUserRole(userRole);
        user.setAddress(address);
        user.setModifyBy(userSession.getId());
        user.setModifyDate(new Date());
        if (userService.update(user) == 1){
            jsonData.setStatus("200");
            jsonData.setMessage("更新成功");
        } else {
            jsonData.setStatus(String.valueOf(CurStatus.ERROR));
            jsonData.setMessage("更新失败");
        }
        return jsonData;
    }

    @RequestMapping(value = "/checkUserCode.action")
    @ResponseBody
    public JSONData checkUserCode(@RequestParam(value = "userCode") String userCode){
        Map<String,Object> userMap = new HashMap<>();
        userMap.put("userCode",userCode);
        if (userService.getUserByMap(userMap) != null){
            jsonData.setStatus(String.valueOf(CurStatus.FIELD_EXISTS));
            jsonData.setMessage("字段已存在");
            jsonData.setData(null);
        } else {
            jsonData.setStatus("200");
            jsonData.setMessage("可以使用");
            jsonData.setData(null);
        }
        return jsonData;
    }

    @RequestMapping(value = "/addUser.action")
    @ResponseBody
    public JSONData add(@RequestParam(value = "userCode") String userCode,
                           @RequestParam(value = "userName") String userName,
                           @RequestParam(value = "userPassword") String userPassword,
                           @RequestParam(value = "gender") Integer gender,
                           @RequestParam(value = "birthday") String birthday,
                           @RequestParam(value = "phone") String phone,
                           @RequestParam(value = "userRole") Integer userRole,
                           @RequestParam(value = "address") String address,
                           HttpSession session) {
        SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd" );
        Date birthdayDate = null;
        try {
            birthdayDate = sdf.parse(birthday);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        User userSession = (User) session.getAttribute("userSession");
        User user = new User();
        user.setId(0);
        user.setUserCode(userCode);
        user.setUserName(userName);
        user.setUserPassword(userPassword);
        user.setGender(gender);
        user.setBirthday(birthdayDate);
        user.setPhone(phone);
        user.setUserRole(userRole);
        user.setAddress(address);
        user.setCreationDate(new Date());
        user.setCreatedBy(userSession.getId());
        if (userService.add(user) == 1){
            jsonData.setStatus("200");
            jsonData.setMessage("添加成功");
        } else {
            jsonData.setStatus(String.valueOf(CurStatus.ERROR));
            jsonData.setMessage("添加失败");
        }
        return jsonData;
    }

    @RequestMapping(value = "/deleteUser.action")
    @ResponseBody
    public JSONData deleteUser(@RequestParam(value = "id") Integer id,HttpSession session){
        User user = userService.getUserById(id);
        User userSession = (User) session.getAttribute("userSession");
        if (userSession.getId() == id){
            jsonData.setStatus(String.valueOf(CurStatus.DEL_ERROR));
            jsonData.setMessage("这个账户是您登录的账户，不能进行删除！");
            return jsonData;
        } else {
            if (user == null){
                jsonData.setStatus(String.valueOf(CurStatus.OBJ_NOTEXISTS));
                jsonData.setMessage("用户不存在！");
                return jsonData;
            } else {
                if (userService.deleteUserById(id) == 1){
                    jsonData.setStatus("200");
                    jsonData.setMessage("删除成功！");
                    return jsonData;
                } else {
                    jsonData.setStatus(String.valueOf(CurStatus.ERROR));
                    jsonData.setMessage("删除失败！");
                    return jsonData;
                }
            }
        }
    }


    @RequestMapping(value = "/checkOldPwd.action")
    @ResponseBody
    public JSONData checkOldPwd(@RequestParam(value = "oldPwd") String oldPwd,
                                HttpSession session){
        User userSession = (User) session.getAttribute("userSession");
        if (userSession != null){
            if ("".equals(oldPwd)){
                jsonData.setStatus(String.valueOf(CurStatus.STRING_ERROR));
                jsonData.setMessage("密码不能为空字符串");
            } else {
                if (userSession.getUserPassword().equals(oldPwd)){
                    jsonData.setStatus("200");
                    jsonData.setMessage("密码正确");
                } else {
                    jsonData.setStatus(String.valueOf(CurStatus.USERPWD_ERROR));
                    jsonData.setMessage("密码错误");
                }
            }

        } else {
            jsonData.setStatus(String.valueOf(CurStatus.SESSION_ERROR));
            jsonData.setMessage("当前session已过期");
        }
        System.out.println(jsonData.toString());
        return jsonData;
    }

    @RequestMapping(value = "/modifyPwd")
    @ResponseBody
    public JSONData modifyPwd(@RequestParam(value = "oldPwd") String oldPwd,
                              @RequestParam(value = "newPwd") String newPwd,
                              HttpSession session){
        User userSession = (User) session.getAttribute("userSession");
        User user = new User();
        user.setId(userSession.getId());
        user.setUserPassword(newPwd);
        if (userService.update(user) == 1){
            jsonData.setStatus("200");
            jsonData.setMessage("更新成功，请重新登录！");
            session.removeAttribute("userSession");
        } else {
            jsonData.setStatus(String.valueOf(CurStatus.ERROR));
            jsonData.setMessage("更新失败，服务器发生错误！");
        }
        return  jsonData;
    }

}
