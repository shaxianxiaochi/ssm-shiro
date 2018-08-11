package com.ssm.controller;

import com.ssm.pojo.Jump;
import com.ssm.service.JumpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/ssm")
public class JumpController {

    @Autowired(required = false)
    private JumpService jumpService;

    public JumpService getJumpService() {
        return jumpService;
    }

    public void setJumpService(JumpService jumpService) {
        this.jumpService = jumpService;
    }


    @RequestMapping(value = "/getQuery.do")
    @ResponseBody
    public Object getQuery(@RequestParam(value = "name") String name){
        Map<String,Object> map=new HashMap<>();
        List<Jump> list;
        list=jumpService.getQuery(map);
        if (name!=null && !name.equals("")){
            map.put("name",name);
            list=jumpService.getQuery(map);
        }
        System.out.println(list.toString());
        return list;
    }
}
