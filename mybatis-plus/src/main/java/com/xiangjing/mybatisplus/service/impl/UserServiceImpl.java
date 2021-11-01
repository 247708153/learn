package com.xiangjing.mybatisplus.service.impl;

import com.xiangjing.mybatisplus.entity.User;
import com.xiangjing.mybatisplus.mapper.UserMapper;
import com.xiangjing.mybatisplus.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiangjing
 * @since 2021-11-01
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
