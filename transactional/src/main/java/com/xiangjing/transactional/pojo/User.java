package com.xiangjing.transactional.pojo;

import lombok.Data;

/**
 * @author : xiangjing
 * @version : 1.0
 * @className : User
 * @date : 2021/10/25 - 16:11
 * @description : <user>
 */
@Data
public class User {
    private Long id;
    private String name;
    private Integer age;
    private String email;
}
