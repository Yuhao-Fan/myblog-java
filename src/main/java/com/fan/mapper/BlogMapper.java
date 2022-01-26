package com.fan.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fan.entity.Blog;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 关注公众号：MarkerHub
 * @since 2021-01-22
 */
public interface BlogMapper extends BaseMapper<Blog> {
//    @Select("select t1.*,t2.username from blog t1 LEFT JOIN  user t2  ON t1.user_id =t2.id WHERE t2.username= #{name}")
//    List<Object> blogUserList(String name);
}
