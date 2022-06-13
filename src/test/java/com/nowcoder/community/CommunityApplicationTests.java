package com.nowcoder.community;

import com.nowcoder.community.config.AlphaConfig;
import com.nowcoder.community.dao.AlphaDao;
import com.nowcoder.community.service.AlphaService;
//import org.junit.jupiter.api.Test;
import org.junit.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
//得到main中的环境，测试才有意义
@ContextConfiguration(classes = CommunityApplication.class)
class CommunityApplicationTests implements ApplicationContextAware {

	private ApplicationContext applicationContext; // 定义一个高级容器

	// 接口函数的override implement IDEA快捷键是 Ctrl I
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	//一个测试函数
	@Test
	public void testApplicationContext() {
		// 介绍从容器获取Bean的基本方式
		System.out.println(applicationContext); // 构造了一个容器
		// 获得AlphaDao的容器
		// getBean 有不同的调用方式
		AlphaDao alphaDao = applicationContext.getBean(AlphaDao.class); // 将该容器获取一个Bean，该Bean中的内容就是AlphoDao  返回一个alpha对象
		// 封装的优势是方便更换技术，选择不同的容器内容
		System.out.println(alphaDao.select());
		// 有选择的调用选择alpha的内容
		alphaDao = applicationContext.getBean("alphaharbinate", AlphaDao.class);
		System.out.println(alphaDao.select());
	}

	@Test
	public void testBeanManagement() {
		// spring 管理bean内容的生成销毁
		// 被spring管理的容器，bean只被实例化一次，单例程序，即使下面的代码重复，依然是一个bean实例
		// 使用 @Scope("prototype") // 可以导致多个实例，而不是只实例化一个bean，一般一个web开发一种对象只有一个
		AlphaService alphaService = applicationContext.getBean(AlphaService.class); // 根据类获取容器
		System.out.println(alphaService);
//		alphaService = applicationContext.getBean(AlphaService.class);
//		System.out.println(alphaService);
	}

	// 第三方的类 jar包
	@Test // simpledataformat?
	public void testBeanConfig() {
		SimpleDateFormat simpleDateFormat = // 返回对象的类型
				applicationContext.getBean(SimpleDateFormat.class); // 我怎么感觉这里有问题呢？
		System.out.println(simpleDateFormat.format(new Date()));
	}

	@Test // simpledataformat 获得Config 容器来得到结果
	public void testBeanConfig2() {
		AlphaConfig alphaConfig = // 返回对象的类型
				applicationContext.getBean(AlphaConfig.class); // 对上面的测试方法的重写
		System.out.println(alphaConfig.simpleDateFormat().format(new Date()));
	}

	// 注入依赖的简单方法
	@Autowired
	@Qualifier("alphaharbinate") // 修改实现方式
	private AlphaDao alphaDao; // 将AlphoDao注入属性

	@Autowired
	private AlphaService alphaService;

	@Autowired
	private SimpleDateFormat simpleDateFormat;

	@Test
	public void testDI(){
		System.out.println(alphaDao);
		System.out.println(alphaService);
		System.out.println(simpleDateFormat);
	}

}