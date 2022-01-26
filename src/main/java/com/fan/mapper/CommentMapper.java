package com.fan.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fan.entity.Comment;
import org.apache.ibatis.annotations.Select;
import java.util.Map;
import java.util.List;

public interface CommentMapper extends BaseMapper<Comment> {
    @Select("select t1.*,t2.username,t2.avatar from comment t1 LEFT JOIN user t2 ON t1.user_id =t2.id Where t1.blog_id= #{blogId}")
    List<Map<String,Object>> commentUserList(Integer blogId);

    @Select("select commentandusername.*,blog.title from (select comment.*,user.username from comment LEFT JOIN user ON comment.user_id=user.id) commentandusername LEFT JOIN blog ON commentandusername.blog_id =blog.id Where blog.user_id= #{userId}")
    List<Map<String,Object>> receivedComment(Integer userId);
}
