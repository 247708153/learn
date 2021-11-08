package com.xiangjing.redis;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import lombok.extern.slf4j.Slf4j;
import org.jasypt.util.text.AES256TextEncryptor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;

/**
 * @author : xiangjing
 * @version : 1.0
 * @className : GeneratorTest
 * @date : 2021/10/29 - 15:14
 * @description : <mybatis一键式生成代码>
 */
@Slf4j
@SpringBootTest
public class GeneratorTest {

    @BeforeAll
    public static  void before() {
        System.setProperty("jasypt.encryptor.password", System.getenv("JASYPT_PASSWORD"));
    }


    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.redis.password}")
    private String redisPwd;

    //需要生成的表名
    private String generateTable="user";
    //作者
    private String author="xiangjing";
    //输出目录
    private String outputDir="D:\\ideaProjects\\xiangjing\\learn\\redis\\src\\main\\";
    //输出目录
    private String outputCodeDir="D:\\ideaProjects\\xiangjing\\learn\\redis\\src\\main\\java\\";

    /**
     * 数据源配置
     */
    @Test
    public void generate_mp(){
        FastAutoGenerator.create(url, username, password)
                .globalConfig(builder -> {
                    builder.author(author) // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .disableOpenDir()
                            .fileOverride() // 覆盖已生成文件
                            .outputDir(outputCodeDir); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.xiangjing.redis") // 设置父包名
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, outputDir + "resources\\mapper"));// 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude(generateTable); // 设置需要生成的表名
//                            .addTablePrefix("t_", "c_"); // 设置过滤表前缀
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }

    @Test
    public void test() {
        AES256TextEncryptor basicTextEncryptor=new AES256TextEncryptor();
        basicTextEncryptor.setPassword(System.getenv("JASYPT_PASSWORD"));
        String encryptUrl = basicTextEncryptor.encrypt(redisPwd);
        log.info("encryptUrl[ENC({})]",encryptUrl);
    }
}
