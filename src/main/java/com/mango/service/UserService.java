package com.mango.service;

import com.mango.pojo.Users;

/**
 * @author wjy
 */
public interface UserService {

    /**
     * 判断用户名是否存在
     * @param userName
     * @return
     */
    public boolean queryUsernameIsExist(String userName);

    /**
     * 查询用户是否存在
     * @param userName
     * @param password
     * @return
     */
    public Users queryUserForLogin(String userName,String password);

    /**
     * 用户注册
     * @param user
     * @return
     */
    public Users saveUser(Users user);
}
