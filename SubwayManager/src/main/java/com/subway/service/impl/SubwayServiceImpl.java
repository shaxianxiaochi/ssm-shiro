package com.subway.service.impl;

import com.subway.mapper.SubwayMapper;
import com.subway.pojo.SubwayInfo;
import com.subway.service.SubwayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 地铁线路业务实现类
 */
@Service("subwayServiceImpl")
public class SubwayServiceImpl implements SubwayService {

    @Autowired
    private SubwayMapper subwayMapper;
    @Override
    public List<SubwayInfo> getList() {
        return subwayMapper.getList();
    }

    @Override
    public SubwayInfo getSubwayInfoById(Integer id) {
        return subwayMapper.getSubwayInfoById(id);
    }

    @Override
    public int update(SubwayInfo subwayInfo) {
        return subwayMapper.update(subwayInfo);
    }

    @Override
    public int delete(int id) {
        return subwayMapper.delete(id);
    }
}
