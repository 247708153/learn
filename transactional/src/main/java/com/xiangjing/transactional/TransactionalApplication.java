package com.xiangjing.transactional;

import org.jasypt.encryption.StringEncryptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

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
