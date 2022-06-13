package com.nowcoder.community;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration;

@SpringBootApplication  // 代表是一个主程序的入口
//@EnableAutoConfiguration(exclude = {ThymeleafAutoConfiguration.class})
public class CommunityApplication {
	// 扫描com.nowcoder.community 子包下面的所有容器，容器是有注释标记的四种
	public static void main(String[] args) {
		SpringApplication.run(CommunityApplication.class, args);
	}

}
