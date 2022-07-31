package com.pepsiwyl.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pepsiwyl.pojo.User;

/**
 * @author by pepsi-wyl
 * @date 2022-07-30 14:59
 */

public interface UserService extends IService<User> {

    // 登陆
    User login(User user);
}
