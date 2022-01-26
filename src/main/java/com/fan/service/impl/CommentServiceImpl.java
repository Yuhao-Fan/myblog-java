package com.fan.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fan.entity.Comment;
import com.fan.mapper.CommentMapper;
import com.fan.service.CommentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {
    @Override
    public List<Map<String,Object>> commentUserList(Integer blogId) {

        return  this.baseMapper.commentUserList(blogId);
    }
    @Override
    public List<Map<String,Object>> receivedComment(Integer userId){
        return  this.baseMapper.receivedComment(userId);
    }

}