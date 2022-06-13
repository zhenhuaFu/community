package com.nowcoder.community.service;

import com.nowcoder.community.dao.AlphaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Service // 代表可以被扫描到的一个容器
//@Scope("prototype") // 可以导致多个实例，而不是只实例化一个bean，一般一个web开发一种对象只有一个
public class AlphaService {

    @Autowired // 注入数据端的依赖
    private AlphaDao alphaDao;

    AlphaService(){
        System.out.println("实例化alphaservice");
    }

    @PostConstruct
    public void init(){
        System.out.println("初始化alphaservice");
    }
    @PreDestroy
    public void destory(){
        System.out.println("销毁alphaservice");
    }

    public String find(){
        return alphaDao.select();
    }
}
