package com.smbms.test;

import com.smbms.dao.UserMapper;
import com.smbms.pojo.User;
import com.smbms.util.MyBatisUtil;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


public class UserMapperTest {
    @Test
    public void testGetUserListByObj(){
        SqlSession session = null;
        User user = new User();
        user.setUserName("孙");

        try {
            session = MyBatisUtil.openSession();

            List<User> list = session.getMapper(UserMapper.class).getUserListByObj(user);
            for (User user1 : list) {
                System.out.println(user1.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MyBatisUtil.closeSession(session);
        }

        System.out.println("aaaa");
    }
}
