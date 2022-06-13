package com.nowcoder.community.dao; //dao的目录要在com目录下

import org.springframework.stereotype.Repository;

@Repository("alphaharbinate") // 定义一个容器内容的label方便有选择地调用，可以随便改，但语义尽量一致
public class AlphaDaoharbinateImpl implements AlphaDao{ // 对接口的实现
    @Override
    public String select() {
        return "Haibinate";
    }
}
