package com.example.demo.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.dao.SongListMapper;
import com.example.demo.domain.SongList;
import com.example.demo.service.SongListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class SongListServiceImpl extends ServiceImpl<SongListMapper, SongList>
    implements SongListService {
  @Autowired StringRedisTemplate redisTemplate;
  @Autowired private SongListMapper songListMapper;

  @Override
  public boolean updateSongListMsg(SongList songList) {
    return songListMapper.updateSongListMsg(songList) > 0 ? true : false;
  }

  @Override
  public boolean deleteSongList(Integer id) {
    return songListMapper.deleteSongList(id) > 0 ? true : false;
  }

  @Cacheable(value = {"songList"}, key = "#root.methodName")
  @Override
  public List<SongList> allSongList() {
    SongListMapper baseMapper = this.baseMapper;
    return baseMapper.selectList(null);
  }

  @Override
  public List<SongList> likeTitle(String title) {
    SongListMapper baseMapper = this.baseMapper;
    return baseMapper.selectList(new QueryWrapper<SongList>().like("title",title));
  }

  @Override
  public List<SongList> likeStyle(String style) {
    List<SongList> SongList = null;
    // 查redis
    String SongListStyle = redisTemplate.opsForValue().get(style);
    redisTemplate.delete(style);
    if (!StringUtils.isEmpty(SongListStyle)) {
      SongList = JSON.parseObject(SongListStyle, new TypeReference<List<SongList>>() {});
      System.out.println("查redis");
      return SongList;
    }
    System.out.println("查数据库");
    SongListMapper baseMapper = this.baseMapper;
    SongList = baseMapper.selectList(new QueryWrapper<SongList>().like("style", style));
    String s = JSON.toJSONString(SongList);
    redisTemplate.opsForValue().set(style, s, 1, TimeUnit.DAYS);
    return SongList;
  }

  @Override
  public List<SongList> songListOfTitle(String title) {
    return songListMapper.songListOfTitle(title);
  }

  @Override
  public boolean addSongList(SongList songList) {
    return songListMapper.insertSelective(songList) > 0 ? true : false;
  }

  @Override
  public boolean updateSongListImg(SongList songList) {

    return songListMapper.updateSongListImg(songList) > 0 ? true : false;
  }
}
