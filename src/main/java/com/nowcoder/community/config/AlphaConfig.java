package com.nowcoder.community.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;

@Configuration // 声明这是一个config文件
public class AlphaConfig {

    @Bean // bean的名字就是下面的方法名字
    public SimpleDateFormat simpleDateFormat(){  // 后向后面的方法名叫什么无所谓
        //方法返回的对象装备到容器里面
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    }
}
