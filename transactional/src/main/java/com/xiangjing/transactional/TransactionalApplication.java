package com.xiangjing.transactional;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author xiangjing
 */
@SpringBootApplication
@MapperScan("com.xiangjing.transactional.mapper")
public class TransactionalApplication{

    public static void main(String[] args) {
        SpringApplication.run(TransactionalApplication.class, args);
    }

}
