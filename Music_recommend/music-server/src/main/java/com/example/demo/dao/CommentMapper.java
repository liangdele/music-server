package com.example.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.domain.Comment;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentMapper extends BaseMapper<Comment> {

  int insertSelective(Comment record);

  Comment selectByPrimaryKey(Integer id);

  int updateByPrimaryKey(Comment record);

  int updateCommentMsg(Comment record);

  int deleteComment(Integer id);

  List<Comment> allComment();
}
