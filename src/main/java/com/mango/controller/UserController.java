package com.mango.controller;

import com.mango.pojo.vo.UsersVO;
import com.mango.service.UserService;
import com.mango.utils.MD5Utils;
import com.mango.utils.MangoJSONResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mango.pojo.Users;

/**
 * @author wjy
 */
@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/registerOrLogin")
    public MangoJSONResult registerOrLogin(@RequestBody Users user) throws Exception {

        if(StringUtils.isBlank(user.getUsername()) || StringUtils.isBlank(user.getPassword())){
           return MangoJSONResult.errorMsg("用户名或密码不能为空...");
        }

        boolean userNameIsExist = userService.queryUsernameIsExist(user.getUsername());
        Users userResult = null;
        if(userNameIsExist){
            //登陆
            userResult = userService.queryUserForLogin(user.getUsername(),MD5Utils.getMD5Str(user.getPassword()));
            if(userResult == null){
                return MangoJSONResult.errorMsg("用户名或密码不正确...");
            }

        } else {
            //注册
            user.setNickname(user.getUsername());
            user.setFaceImage("");
            user.setFaceImageBig("");
            user.setPassword(MD5Utils.getMD5Str(user.getPassword()));
            userResult=userService.saveUser(user);
        }

        UsersVO usersVO = new UsersVO();
        BeanUtils.copyProperties(userResult,usersVO);

        return MangoJSONResult.ok(usersVO);
    }
}
