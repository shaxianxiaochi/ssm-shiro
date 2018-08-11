package com.bbs.mapper;

import com.bbs.pojo.BbsDetail;

import java.util.List;
import java.util.Map;

public interface BbsDetailMapper {


    /**
     * 查询全部信息和版区进行查询
     * @param map
     * @return
     */
    List<BbsDetail> getAllOrName(Map<String,Object> map);

    /**
     * 添加信息
     * @param bbsDetail
     * @return
     */
    int insert(BbsDetail bbsDetail);

    /**
     * 添加信息时异步进行判断是都否存在
     * @param title
     * @return
     */
    BbsDetail selectOne(String title);
}
