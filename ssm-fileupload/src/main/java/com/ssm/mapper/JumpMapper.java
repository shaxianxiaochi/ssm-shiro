package com.ssm.mapper;

import com.ssm.pojo.Jump;

import java.util.List;
import java.util.Map;

public interface JumpMapper {

    /**
     * 查询信息
     * @param map  存放数据
     * @return
     */
    List<Jump> getQuery(Map<String,Object> map);
}
