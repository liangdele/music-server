package com.example.demo.service;

import com.example.demo.domain.Comment;

import java.util.List;

public interface CommentService {

    int addComment(Comment comment);

    int updateCommentMsg(Comment comment);

    boolean deleteComment(Integer id);

    List<Comment> allComment();

    List<Comment> commentOfSongId(Integer songId);

    List<Comment> commentOfSongListId(Integer songListId);
   void checkUCommentUnique(String comment);
}
