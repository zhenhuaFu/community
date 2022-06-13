package com.nowcoder.community.dao;

import com.nowcoder.community.entity.DiscussPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Mapper // 扫描 装配
public interface DiscussPostMapper {

    // 有时需要传参，有时不需要，因为看评论不需要搜id，但是看自己发布过的帖子，需要id
    // 通过sql动态条件
    // 还要考虑分页， sql种需要起始行号和最多的一页条数
    List<DiscussPost> selectDiscussPosts(int userId, int offset, int limit);
    // 查询总行数
    // 如果需要sql动态的拼一个条件，并且方法只有一个参数，则参数必须取别名，否则报错
    int selectDiscussPostRows(@Param("userId") int userId); //Param给参数取个别名


}
