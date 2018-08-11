package com.ssm.service;

import com.ssm.pojo.Jump;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;



public interface JumpService {

    /**
     * 查询信息
     * @param map  存放数据
     * @return
     */
    List<Jump> getQuery(Map<String,Object> map);
}
