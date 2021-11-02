package com.xiangjing.transactional.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiangjing.transactional.entity.User;
import com.xiangjing.transactional.mapper.UserMapper;
import com.xiangjing.transactional.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiangjing
 * @since 2021-11-01
 */
@Slf4j
@Service
public class CallYourself extends ServiceImpl<UserMapper, User> implements IUserService {

    private UserMapper userMapper;

    @Resource
    private CallYourself callYourself;

    public CallYourself(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    /**
     * 自己調用自己 多个事务 分别回滚
     * 预期：用户添加成功，修改成功 报错后 不添加 不修改
     * 结果：事务分别回滚
     * @param isThrow
     * @throws Exception
     */
    public void callYourself(Integer isThrow) throws Exception{
        callYourself.addUser();
        callYourself.updateUser(isThrow);
    }
    /**
     * 自己調用自己 保證事務
     * 预期：用户添加成功，修改成功 报错后 不添加 不修改
     * 结果：一致
     * @param isThrow
     * @throws Exception
     */
    public void callYourself2(Integer isThrow) throws Exception{
        callYourself.addAndUpdate(isThrow);
    }

    @Transactional(rollbackFor = Exception.class)
    public void addAndUpdate(Integer isThrow) throws Exception{
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
    public void addUser(){
        User user = new User();
        user.setId(RandomUtil.randomLong(0,99999));
        user.setName("add");
        user.setAge(RandomUtil.randomInt(0,100));
        user.setEmail(RandomUtil.randomString(5));
        log.info("add user[{}]",user);
        userMapper.insert(user);
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateUser(Integer isThrow) throws Exception{
        User user = new User();
        user.setId(1L);
        user.setName("update");
        user.setAge(RandomUtil.randomInt(0,100));
        user.setEmail(RandomUtil.randomString(5));
        log.info("add update[{}]",user);
         userMapper.updateById(user);
         throwException(isThrow);
    }

    private void throwException(Integer isThrow) throws Exception {
        if(isThrow !=null && Integer.valueOf(1).equals(isThrow)){
            throw new Exception();
        }
    }
}
