package com.xiangjing.redis.service.impl;

import com.xiangjing.redis.entity.User;
import com.xiangjing.redis.mapper.UserMapper;
import com.xiangjing.redis.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiangjing
 * @since 2021-11-02
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
