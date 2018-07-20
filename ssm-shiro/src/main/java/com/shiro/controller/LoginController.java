package com.shiro.controller;

import com.shiro.pojo.User;
import com.shiro.pojo.UserData;
import com.shiro.service.shiroService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.apache.shiro.subject.Subject;

import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
public class LoginController {

    private static final Logger logger= LoggerFactory.getLogger(LoginController.class);

    @Autowired(required = false)
    private shiroService shiroService;


    @RequestMapping("/unlogin")
    public String unlogin(HttpSession session){
        return "un";
    }

    @RequestMapping("/readData")
    public String readData(Model model){
        List<UserData> userDataList=shiroService.getAll();
        model.addAttribute("userdata",userDataList);
        return  "data";
    }

    @RequestMapping("/readName")
    public String readName(){
        return "readName";
    }

    @RequestMapping("/index")
    public String index(){
        return  "index";
    }

    @RequestMapping("/login")
    public String login(String username, String password, HttpSession session, Model model){
        if (username==null){
            model.addAttribute("message","账号不能为空");
            return "login";
        }
        //主体，当前状态为没有认证的状态“未认证”
        Subject subject= SecurityUtils.getSubject();

        //登录后存放进shiro  token
        UsernamePasswordToken token=new UsernamePasswordToken(username,password);
        User user;

        //登录方法，认证是否通过
        //使用subject调用securityManager，安全管理器调用Realm
        try {
            //利用异常操作
            //需要开始调用到Realm中
            System.out.println("================================");
            System.out.println("1、进入认证方法");
            subject.login(token);
            user= (User) subject.getPrincipal();
            session.setAttribute("user",subject);
            model.addAttribute("user",user);
            model.addAttribute("message","登陆完成");
            System.out.println("登陆完成");
        } catch (Exception e) {
            model.addAttribute("message","账号或者密码错误");
            e.printStackTrace();
            return "login";
        }
        return "test";
    }


    @RequestMapping("/check")
    public String check(HttpSession session){
        Subject subject= (Subject) session.getAttribute("user");
        User user= (User) subject.getPrincipal();
        System.out.println(user.toString());
        return "permission";
    }
}
