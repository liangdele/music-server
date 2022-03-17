package com.example.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.domain.Song;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongMapper extends BaseMapper<Song> {

  int insert(Song record);

  int insertSelective(Song record);

  Song selectByPrimaryKey(Integer id);

  int updateByPrimaryKeyWithBLOBs(Song record);

  int updateByPrimaryKey(Song record);

  int updateSongMsg(Song record);

  int updateSongUrl(Song record);

  int updateSongPic(Song record);

  int deleteSong(Integer id);

  List<Song> allSong();

  List<Song> songOfName(String name);
}
