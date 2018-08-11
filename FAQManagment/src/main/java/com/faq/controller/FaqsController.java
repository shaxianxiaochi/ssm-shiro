package com.faq.controller;


import com.faq.pojo.Faqs;
import com.faq.service.FaqsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/faqs")
public class FaqsController {

    @Autowired
    private FaqsService faqsService;

    public FaqsService getFaqsService() {
        return faqsService;
    }

    public void setFaqsService(FaqsService faqsService) {
        this.faqsService = faqsService;
    }

    @RequestMapping(value = "/getFaqs.action")
    public String getFaqs(Model model, @RequestParam(value = "cName",required = false) String cName){
        Map map=new HashMap();
        List<Faqs> faqs = faqsService.getBy(map);
        if(cName!=null){
            map.put("cName",cName);
            faqs = faqsService.getBy(map);
            model.addAttribute("cName",cName);
        }
        model.addAttribute("faqs",faqs);
        return "index";
    }

    @RequestMapping(value = "/getOne.action")
    public String getOne(Model model, @RequestParam(value = "id",required = false) Integer id){
        Faqs title = faqsService.getByTitle(id);
        model.addAttribute("faqs",title);
        return "getOne";
    }

    @RequestMapping(value = "/jump.action")
    public String jump(Model model){
        List<Faqs> faqsList = faqsService.getBy(null);
        model.addAttribute("faqsList",faqsList);
        return "insert";
    }


    @RequestMapping(value = "/insert.action")
    public String insert(Faqs faqs){
        faqs.setCreateDate(new Date());
        int i = faqsService.insert(faqs);
        if(i>0){
            return "redirect:/faqs/getFaqs.action";
        }
        return "redirect:/faqs/jump.action";
    }

}
