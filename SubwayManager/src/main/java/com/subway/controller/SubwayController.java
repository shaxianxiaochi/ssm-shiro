package com.subway.controller;

import com.subway.pojo.SubwayInfo;
import com.subway.service.SubwayService;
import com.subway.tools.JSONData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/subway")
public class SubwayController {

    @Autowired
    private SubwayService subwayService;

    private JSONData jsonData = new JSONData();

    @RequestMapping("/getSubwayList")
    @ResponseBody
    public JSONData getSubwayInfoList(){
        List<SubwayInfo> subwayInfoList = subwayService.getList();
        if (subwayInfoList != null){
            jsonData.setStatus("200");
            jsonData.setMessage("查询成功！");
            jsonData.setData(subwayInfoList);
        } else {
            jsonData.setStatus("999");
            jsonData.setMessage("查询失败！");
            jsonData.setData(null);
        }
        return jsonData;
    }

    @RequestMapping("/getSubwayById")
    @ResponseBody
    public JSONData getSubwayInfoById(@RequestParam(value = "id") Integer id){
        SubwayInfo subwayInfo = subwayService.getSubwayInfoById(id);
        if (subwayInfo != null){
            jsonData.setStatus("200");
            jsonData.setMessage("查询成功！");
            jsonData.setData(subwayInfo);
        } else {
            jsonData.setStatus("999");
            jsonData.setMessage("查询失败！");
            jsonData.setData(null);
        }
        return jsonData;
    }

    @RequestMapping("/update")
    @ResponseBody
    public JSONData updateSubwayInfo(@RequestParam(value = "id") Integer id,
                                     @RequestParam(value = "subwayName") String subwayName,
                                     @RequestParam(value = "startStation") String startStation,
                                     @RequestParam(value = "endStation") String endStation,
                                     @RequestParam(value = "stationNum") Integer stationNum,
                                     @RequestParam(value = "startTime") String startTime,
                                     @RequestParam(value = "price") Integer price){
        SubwayInfo subwayInfo = new SubwayInfo(id,subwayName,startStation,endStation,stationNum,startTime,price);
        if (subwayService.update(subwayInfo) == 1){
            jsonData.setStatus("200");
            jsonData.setMessage("更新成功！");
            jsonData.setData(null);
        } else {
            jsonData.setStatus("999");
            jsonData.setMessage("更新失败！");
            jsonData.setData(null);
        }
        return jsonData;
    }

    @RequestMapping(value = "/delete")
    @ResponseBody
    public JSONData delete(@RequestParam(value = "id") Integer id){
        if (subwayService.delete(id) == 1){
            jsonData.setStatus("200");
            jsonData.setMessage("删除成功！");
            jsonData.setData(null);
        } else {
            jsonData.setStatus("999");
            jsonData.setMessage("删除失败！");
            jsonData.setData(null);
        }
        return jsonData;
    }
}
