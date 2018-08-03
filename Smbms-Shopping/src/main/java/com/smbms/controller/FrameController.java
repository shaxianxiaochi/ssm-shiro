package com.smbms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/admin")
public class FrameController {

    @RequestMapping(value = "billlist.action")
    public String sendBillList(){
        return "billlist";
    }

    @RequestMapping(value = "billadd.action")
    public String sendBilladd(){
        return "billadd";
    }

    @RequestMapping(value = "billmodify.action")
    public String sendBillmodify(){
        return "billmodify";
    }

    @RequestMapping(value = "billview.action")
    public String sendBillview(){
        return "billview";
    }

    @RequestMapping(value = "providerlist.action")
    public String sendProviderlist(){
        return "providerlist";
    }


}
