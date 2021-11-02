package com.xiangjing.transactional.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiangjing.transactional.entity.User;
import com.xiangjing.transactional.mapper.UserMapper;
import com.xiangjing.transactional.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class BigTransaction extends ServiceImpl<UserMapper, User> implements IUserService {

    private UserMapper userMapper;

    public BigTransaction(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    /**
     * private 和 final 修饰符会直接在编译时报错
     * 预期：用户添加成功，修改成功 报错后 不添加 不修改
     * 结果：一致
     * 在方法上使用事务 调用内部方法 方法内部报错 全部回滚
     * @param isThrow
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public void bigTransaction(Integer isThrow) throws Exception{
        addUser();
//        throwException(isThrow);
        updateUser(isThrow);
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

    private void updateUser(Integer isThrow) throws Exception{
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
