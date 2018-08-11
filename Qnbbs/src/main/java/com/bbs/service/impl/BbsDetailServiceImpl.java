package com.bbs.service.impl;

import com.bbs.mapper.BbsDetailMapper;
import com.bbs.pojo.BbsDetail;
import com.bbs.service.BbsDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("bbsDetailService")
public class BbsDetailServiceImpl implements BbsDetailService{

    @Autowired(required = false)
    private BbsDetailMapper bbsDetailMapper;

    public BbsDetailMapper getBbsDetailMapper() {

        return bbsDetailMapper;
    }

    public void setBbsDetailMapper(BbsDetailMapper bbsDetailMapper) {
        this.bbsDetailMapper = bbsDetailMapper;
    }

    @Override
    public List<BbsDetail> getAllOrName(Map<String, Object> map) {
        return bbsDetailMapper.getAllOrName(map);
    }

    @Override
    public int insert(BbsDetail bbsDetail) {
        return bbsDetailMapper.insert(bbsDetail);
    }

    @Override
    public BbsDetail selectOne(String title) {
        return bbsDetailMapper.selectOne(title);
    }
}
