package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.dao.CommentMapper;
import com.example.demo.domain.Comment;
import com.example.demo.exception.ContentException;
import com.example.demo.exception.UserExistException;
import com.example.demo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment>
    implements CommentService {
  @Autowired private CommentMapper commentMapper;

  @Override
  public int addComment(Comment comment) {
    checkUCommentUnique(comment.getContent());
    CommentMapper baseMapper = this.baseMapper;
    return baseMapper.insert(comment);
  }

  @Override
  public void checkUCommentUnique(String content) throws ContentException {
    if (content == null || content.length() == 0) {
      throw new ContentException();
    }
  }

  @Override
  public int updateCommentMsg(Comment comment) {
    CommentMapper baseMapper = this.baseMapper;
return baseMapper.updateById(comment);
//    return commentMapper.updateCommentMsg(comment) > 0 ? true : false;
  }

  //    删除评论
  @Override
  public boolean deleteComment(Integer id) {
    return commentMapper.deleteComment(id) > 0 ? true : false;
  }

  @Override
  public List<Comment> allComment() {
    return commentMapper.allComment();
  }

  @Override
  public List<Comment> commentOfSongId(Integer songId) {
    CommentMapper baseMapper = this.baseMapper;
    return baseMapper.selectList(new QueryWrapper<Comment>().eq("song_id", songId));
  }

  @Override
  public List<Comment> commentOfSongListId(Integer songListId) {
    CommentMapper baseMapper = this.baseMapper;
    return baseMapper.selectList(new QueryWrapper<Comment>().eq("song_list_id", songListId));
  }
}
