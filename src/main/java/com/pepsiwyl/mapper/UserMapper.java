package com.pepsiwyl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pepsiwyl.pojo.User;
import org.springframework.stereotype.Repository;

/**
 * @author by pepsi-wyl
 * @date 2022-07-30 14:44
 */

@Repository("userMapper")
public interface UserMapper extends BaseMapper<User> {

    // 登陆
    User getUser(User user);

}
