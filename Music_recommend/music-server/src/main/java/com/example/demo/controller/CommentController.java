package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.domain.Comment;
import com.example.demo.exception.BizCodeEnume;
import com.example.demo.exception.ContentException;
import com.example.demo.service.impl.CommentServiceImpl;
import com.example.demo.vo.CommentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
@Controller
public class CommentController {
  @Autowired private CommentServiceImpl commentService;

  //  提交评论
  @ResponseBody
  @PostMapping("/comment/add")
  public Object addComment(CommentVo commentVo) {
    JSONObject jsonObject = new JSONObject();
    System.out.println(commentVo);
    Integer userId = commentVo.getUserId();
    Byte type = commentVo.getType();
//    int songListId = commentVo.getSongListId();
//    int songId = commentVo.getSongId();
    String content = commentVo.getContent();

    Comment comment = new Comment();
    comment.setUserId(userId);
    comment.setType(type);
    if (type == 0) {
      comment.setSongId(commentVo.getSongId());
    } else if (type == 1) {
      comment.setSongListId(commentVo.getSongListId());
    }
    comment.setContent(content);
    comment.setCreateTime(new Date());
    try {
      commentService.addComment(comment);
    } catch (ContentException e) {
      jsonObject.put("code", BizCodeEnume.CONTENT_NULL_EXCEPTION.getCode());
      jsonObject.put("msg", BizCodeEnume.CONTENT_NULL_EXCEPTION.getMsg());
      return jsonObject;
    }

    jsonObject.put("code", 1);
    jsonObject.put("msg", "评论成功");
    return jsonObject;
  }

  //    获取所有评论列表
  @RequestMapping(value = "/comment", method = RequestMethod.GET)
  public Object allComment() {
    return commentService.allComment();
  }

  //    获得指定歌曲ID的评论列表
  @GetMapping( "/comment/song/detail")
  public Object commentOfSongId(Integer songId) {
    return commentService.commentOfSongId(songId);
  }

  //    获得指定歌单ID的评论列表
  @GetMapping( "/comment/songList/detail")
  public Object commentOfSongListId(Integer songListId) {
    return commentService.commentOfSongListId(songListId);
  }

  //    点赞
  @ResponseBody
  @PostMapping("/comment/like")
  public Object commentOfLike(Integer id,Integer up) {

    JSONObject jsonObject = new JSONObject();
    Comment comment = new Comment();
    comment.setId(id);
    comment.setUp(up);
    int res = commentService.updateCommentMsg(comment);
    if (res!=0) {
      jsonObject.put("code", 1);
      jsonObject.put("msg", "点赞成功");
      return jsonObject;
    } else {
      jsonObject.put("code", 0);
      jsonObject.put("msg", "点赞失败");
      return jsonObject;
    }
  }

  //    删除评论
  @RequestMapping(value = "/comment/delete", method = RequestMethod.GET)
  public Object deleteComment(HttpServletRequest req) {
    String id = req.getParameter("id");
    return commentService.deleteComment(Integer.parseInt(id));
  }

  //    更新评论
  @ResponseBody
  @RequestMapping(value = "/comment/update", method = RequestMethod.POST)
  public Object updateCommentMsg(HttpServletRequest req) {
    JSONObject jsonObject = new JSONObject();
    String id = req.getParameter("id").trim();
    String user_id = req.getParameter("userId").trim();
    String song_id = req.getParameter("songId").trim();
    String song_list_id = req.getParameter("songListId").trim();
    String content = req.getParameter("content").trim();
    String type = req.getParameter("type").trim();
    String up = req.getParameter("up").trim();

    Comment comment = new Comment();
    comment.setId(Integer.parseInt(id));
    comment.setUserId(Integer.parseInt(user_id));
    if (song_id == "") {
      comment.setSongId(null);
    } else {
      comment.setSongId(Integer.parseInt(song_id));
    }

    if (song_list_id == "") {
      comment.setSongListId(null);
    } else {
      comment.setSongListId(Integer.parseInt(song_list_id));
    }
    comment.setContent(content);
    comment.setType(new Byte(type));
    comment.setUp(Integer.parseInt(up));

    int res = commentService.updateCommentMsg(comment);
    if (res!=0) {
      jsonObject.put("code", 1);
      jsonObject.put("msg", "修改成功");
      return jsonObject;
    } else {
      jsonObject.put("code", 0);
      jsonObject.put("msg", "修改失败");
      return jsonObject;
    }
  }
}
