package com.xiangjing.transactional.service.impl;

import com.xiangjing.transactional.entity.User;
import com.xiangjing.transactional.mapper.UserMapper;
import com.xiangjing.transactional.service.IUserService;
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
