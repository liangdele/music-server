package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.dao.SingerMapper;
import com.example.demo.dao.SongMapper;
import com.example.demo.domain.Song;
import com.example.demo.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongServiceImpl extends ServiceImpl<SongMapper, Song> implements SongService {

  @Autowired private SongMapper songMapper;

  @Override
  public List<Song> allSong() {
    SongMapper baseMapper = this.baseMapper;
    return baseMapper.selectList(null);
  }

  @Override
  public boolean addSong(Song song) {

    return songMapper.insertSelective(song) > 0 ? true : false;
  }

  @Override
  public boolean updateSongMsg(Song song) {
    return songMapper.updateSongMsg(song) > 0 ? true : false;
  }

  @Override
  public boolean updateSongUrl(Song song) {

    return songMapper.updateSongUrl(song) > 0 ? true : false;
  }

  @Override
  public boolean updateSongPic(Song song) {

    return songMapper.updateSongPic(song) > 0 ? true : false;
  }

  @Override
  public boolean deleteSong(Integer id) {
    return songMapper.deleteSong(id) > 0 ? true : false;
  }

  @Override
  public List<Song> songOfSingerId(Integer singerId) {
    SongMapper baseMapper = this.baseMapper;
    return baseMapper.selectList(new QueryWrapper<Song>().eq("singer_id", singerId));
  }

  @Override
  public List<Song> songOfId(Integer id) {
    SongMapper baseMapper = this.baseMapper;
    return baseMapper.selectList(new QueryWrapper<Song>().eq("id", id));
  }

  @Override
  public List<Song> songOfSingerName(String name) {
    SongMapper baseMapper = this.baseMapper;
    List<Song> songList = baseMapper.selectList(new QueryWrapper<Song>().like("name", name));
    return songList;
  }

  @Override
  public List<Song> songOfName(String name) {

    return songMapper.songOfName(name);
  }
}
