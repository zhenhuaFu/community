//package com.nowcoder.community.dao;
//
//import com.nowcoder.community.entity.User;
//import org.apache.ibatis.annotations.Mapper;
//
//@Mapper // 注解作用同repostory、serverce等相同，这是一个接口函数，接口函数的具体实现再xml文件中，xml就是SQL语句嵌入的形式
//public interface UserMapper {
//    // 和数据库有关的类
//    User selectById(int id);
//
//    User selectByName(String username);
//
//    User selectByEmail(String email);
//
//    int insertUser(User user);
//
//    int updateStatus(int id, int status); // 代表返回改动了几行
//
//    int updateHeader(int id, String headerUrl);
//
//    int updatePassword(int id, String password);
//
//}


package com.nowcoder.community.dao;

import com.nowcoder.community.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    User selectById(int id);

    User selectByName(String username);

    User selectByEmail(String email);

    int insertUser(User user);

    int updateStatus(int id, int status);

    int updateHeader(int id, String headerUrl);

    int updatePassword(int id, String password);

}
