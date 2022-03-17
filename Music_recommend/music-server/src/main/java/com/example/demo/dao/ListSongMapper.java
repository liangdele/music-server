package com.example.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.domain.ListSong;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ListSongMapper extends BaseMapper<ListSong> {

  int insertSelective(ListSong record);

  ListSong selectByPrimaryKey(Integer id);

  int updateByPrimaryKey(ListSong record);

  int updateListSongMsg(ListSong record);

  int deleteListSong(Integer songId);

  List<ListSong> allListSong();
}
