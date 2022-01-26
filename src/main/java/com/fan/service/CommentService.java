package com.fan.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fan.entity.Comment;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 关注公众号：MarkerHub
 * @since 2021-01-22
 */
public interface CommentService extends IService<Comment> {
    List<Map<String,Object>> commentUserList(Integer blogId);
    List<Map<String,Object>> receivedComment(Integer userId);

}
