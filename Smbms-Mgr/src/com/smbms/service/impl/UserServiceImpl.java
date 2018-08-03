package com.smbms.service.impl;

import com.smbms.dao.UserMapper;
import com.smbms.pojo.User;
import com.smbms.service.UserService;
import com.smbms.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class UserServiceImpl implements UserService {

    @Override
    public List<User> getUserListByObj(User user) {
        SqlSession session = null;
        List<User> list = null;
        try {
            session = MyBatisUtil.openSession();
            list = session.getMapper(UserMapper.class).getUserListByObj(user);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MyBatisUtil.closeSession(session);
        }
        return list;
    }
}
