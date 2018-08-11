package com.bbs.controller;

import com.bbs.pojo.BbsDetail;
import com.bbs.service.BbsDetailService;
import com.bbs.tools.JSONData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.annotation.ElementType;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/bbs")
public class BbsDetailController {

    @Autowired(required = false)
    private BbsDetailService bbsDetailService;

    private JSONData jsonData = new JSONData();

    public BbsDetailService getBbsDetailService() {
        return bbsDetailService;
    }

    public void setBbsDetailService(BbsDetailService bbsDetailService) {
        this.bbsDetailService = bbsDetailService;
    }


    @RequestMapping(value = "/getAllOrName.do")
    public String getAllOrName(Model model, @RequestParam(value = "sname", required = false) String sname) {
        Map<String, Object> map = new HashMap<String, Object>();
        System.out.println(sname);
        List<BbsDetail> detailList = bbsDetailService.getAllOrName(map);
        if (sname != null) {
            map.put("sname", sname);
            detailList = bbsDetailService.getAllOrName(map);
        }
        model.addAttribute("detailList", detailList);
        return "getAllOrName";
    }

    @RequestMapping(value = "/getName.do")
    public String getName(Model model) {
        List<BbsDetail> name = bbsDetailService.getAllOrName(null);
        model.addAttribute("name", name);
        return "add";
    }

    @RequestMapping(value = "/getTilte.do")
    @ResponseBody
    public JSONData getTitle(@RequestParam(value = "title") String title) {
        BbsDetail detail = bbsDetailService.selectOne(title);
        if (detail != null) {
            jsonData.setStatus("200");
            jsonData.setData(detail);
        }
        return jsonData;
    }

    @RequestMapping(value = "/add.do")
    public String add(BbsDetail bbsDetail) {
        bbsDetail.setCreateDate(new Date());
        int i = bbsDetailService.insert(bbsDetail);
        if (i == 1) {
            return "redirect:/bbs/getAllOrName.do";
        } else {
            return "add";
        }
    }
}
