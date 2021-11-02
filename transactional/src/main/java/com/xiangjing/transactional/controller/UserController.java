package com.xiangjing.transactional.controller;


import com.xiangjing.transactional.service.NoServiceAnnotation;
import com.xiangjing.transactional.service.NoTransactional;
import com.xiangjing.transactional.service.impl.BigTransaction;
import com.xiangjing.transactional.service.impl.CallYourself;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xiangjing
 * @since 2021-11-01
 */
@RestController
@RequestMapping("/invalid")
public class UserController {
    private BigTransaction userService;
    private NoServiceAnnotation noServiceAnnotation;
    private NoTransactional noTransactional;
    private CallYourself callYourself;

    public UserController(BigTransaction userService, NoServiceAnnotation noServiceAnnotation, NoTransactional noTransactional, CallYourself callYourself) {
        this.userService = userService;
        this.noServiceAnnotation = noServiceAnnotation;
        this.noTransactional = noTransactional;
        this.callYourself = callYourself;
    }

    @GetMapping("method")
    public void method(Integer isThrow) throws Exception{
        userService.bigTransaction(isThrow);
    }

    @GetMapping("noServiceAnnotation")
    public void noServiceAnnotation(Integer isThrow) throws Exception{
        noServiceAnnotation.noServiceAnnotation(isThrow);
    }

    @GetMapping("noTransactional")
    public void noTransactional(Integer isThrow) throws Exception{
        noTransactional.noTransactional(isThrow);
    }
    @GetMapping("noTransactional2")
    public void noTransactional2(Integer isThrow) throws Exception{
        noTransactional.noTransactional2(isThrow);
    }
    @GetMapping("CallYourself")
    public void CallYourself1(Integer isThrow) throws Exception{
        callYourself.callYourself(isThrow);
    }
    @GetMapping("CallYourself2")
    public void CallYourself2(Integer isThrow) throws Exception{
        callYourself.callYourself2(isThrow);
    }

}
