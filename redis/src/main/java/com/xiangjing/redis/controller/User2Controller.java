package com.xiangjing.redis.controller;


import com.xiangjing.redis.entity.User;
import com.xiangjing.redis.service.impl.JetCacheServiceUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xiangjing
 * @since 2021-11-02
 */
@RestController
@RequestMapping("/user2")
public class User2Controller {

    @Autowired
    JetCacheServiceUser jetCacheServiceUser;

    @GetMapping("getList")
    public User select(@RequestBody User user){
        return jetCacheServiceUser.getUserById(user);
    }
    @PostMapping("update")
    public User updateUser(@RequestBody User user){
        return jetCacheServiceUser.updateValue(user);
    }
    @PostMapping("delete")
    public int deleteUser(@RequestBody User user){
        return jetCacheServiceUser.deleteValue(user);
    }
    @PostMapping("deleteAll")
    public void deleteAll(@RequestBody List<User> list){
        jetCacheServiceUser.deleteAll(list);
    }
    @GetMapping("selectList")
    public List<User> selectList(){
        return jetCacheServiceUser.selectList();
    }

}
