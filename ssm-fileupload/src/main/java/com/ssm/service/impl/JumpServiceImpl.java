package com.ssm.service.impl;

import com.ssm.mapper.JumpMapper;
import com.ssm.pojo.Jump;
import com.ssm.service.JumpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("jumpServiceImpl")
public class JumpServiceImpl implements JumpService{

    @Autowired(required = false)
    private JumpMapper jumpMapper;

    public JumpMapper getJumpMapper() {
        return jumpMapper;
    }

    public void setJumpMapper(JumpMapper jumpMapper) {
        this.jumpMapper = jumpMapper;
    }

    @Override
    public List<Jump> getQuery(Map<String, Object> map) {
        return jumpMapper.getQuery(map);
    }
}
