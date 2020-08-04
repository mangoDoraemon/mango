package com.mango.service.impl;

import com.mango.mapper.UsersMapper;
import com.mango.service.UserService;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mango.pojo.Users;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

/**
 * @author wjy
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private Sid sid;

    @Transactional(propagation = Propagation.SUPPORTS,rollbackFor = Exception.class)
    @Override
    public boolean queryUsernameIsExist(String userName) {
        Users user = new Users();
        user.setUsername(userName);
        Users result = usersMapper.selectOne(user);
        return result != null ? true : false;
    }

    @Transactional(propagation = Propagation.SUPPORTS,rollbackFor = Exception.class)
    @Override
    public Users queryUserForLogin(String userName, String password) {
        Example userExample = new Example(Users.class);
        Criteria criteria=userExample.createCriteria();
        criteria.andEqualTo("username",userName);
        criteria.andEqualTo("password",password);
        Users result = usersMapper.selectOneByExample(userExample);
        return result;
    }

    @Override
    public Users saveUser(Users user) {
        String userId =sid.nextShort();
        // TODO 为每个用户生成唯一的二维码
        user.setQrcode("");
        user.setId(userId);
        usersMapper.insert(user);
        return user;
    }
}
