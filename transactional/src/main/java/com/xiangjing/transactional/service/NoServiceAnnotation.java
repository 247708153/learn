package com.xiangjing.transactional.service;

import cn.hutool.core.util.RandomUtil;
import com.xiangjing.transactional.entity.User;
import com.xiangjing.transactional.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 没有service注解 直接启动报错
 * Consider defining a bean of type 'com.xiangjing.transactional.service.NoServiceAnnotation' in your configuration.
 * @author : xiangjing
 * @version : 1.0
 * @className : UserService
 * @date : 2021/10/29 - 11:12
 * @description : <service>
 */
@Slf4j
@Service
public class NoServiceAnnotation {

    private UserMapper userMapper;

    public NoServiceAnnotation(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    /**
     * 沒被spring管理
     * 预期：用户添加成功，修改失败
     * 结果：调用失败
     * @param isThrow
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public void noServiceAnnotation(Integer isThrow) throws Exception{
        addUser();
        throwException(isThrow);
        updateUser();
    }


    private boolean addUser(){
        User user = new User();
        user.setId(RandomUtil.randomLong(0,99999));
        user.setName("add");
        user.setAge(RandomUtil.randomInt(0,100));
        user.setEmail(RandomUtil.randomString(5));
        log.info("add user[{}]",user);
        return userMapper.insert(user)>0;
    }

    private boolean updateUser(){
        User user = new User();
        user.setId(1L);
        user.setName("update");
        user.setAge(RandomUtil.randomInt(0,100));
        user.setEmail(RandomUtil.randomString(5));
        log.info("add update[{}]",user);
        return userMapper.updateById(user)>0;
    }

    private void throwException(Integer isThrow) throws Exception {
        if(isThrow !=null && Integer.valueOf(1).equals(isThrow)){
            throw new Exception();
        }
    }
}
