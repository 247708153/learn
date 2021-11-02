package com.xiangjing.transactional.service;

import cn.hutool.core.util.RandomUtil;
import com.xiangjing.transactional.entity.User;
import com.xiangjing.transactional.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author : xiangjing
 * @version : 1.0
 * @className : UserService
 * @date : 2021/10/29 - 11:12
 * @description : <service>
 */
@Slf4j
@Service
public class NoTransactional {

    private UserMapper userMapper;
    @Autowired
    NoTransactional noTransactional;
    public NoTransactional(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    /**
     * 事务失效
     * 没用transaction注解的方法调用内部有注解的方法
     * 预期：注解失效，报错后不会回滚 用户添加成功，修改失败
     * 结果：一致
     *
     * 加上transaction注解的方法调用内部有注解的方法
     * 预期：注解生效，报错后回滚 用户添加失败，修改失败
     * 结果：一致
     *
     * @param isThrow
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public void noTransactional(Integer isThrow) throws Exception{
        addAndUpdate(isThrow);
    }

    @Transactional(rollbackFor = Exception.class)
    public void noTransactional2(Integer isThrow) throws Exception{
        addAndUpdate2(isThrow);
    }

    @Transactional(rollbackFor = Exception.class)
    public void addAndUpdate2(Integer isThrow) throws Exception{
        User user = new User();
        user.setId(RandomUtil.randomLong(0,99999));
        user.setName("add");
        user.setAge(RandomUtil.randomInt(0,100));
        user.setEmail(RandomUtil.randomString(5));
        log.info("add user[{}]",user);
        userMapper.insert(user);
        throwException(isThrow);
        User update = new User();
        update.setId(1L);
        update.setName("update");
        update.setAge(RandomUtil.randomInt(0,100));
        update.setEmail(RandomUtil.randomString(5));
        log.info("add update[{}]",update);
        userMapper.updateById(update);
    }


    @Transactional(rollbackFor = Exception.class)
    public void addAndUpdate(Integer isThrow) throws Exception{
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
