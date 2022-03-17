package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.dao.CollectMapper;
import com.example.demo.domain.Collect;
import com.example.demo.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectServiceImpl extends ServiceImpl<CollectMapper, Collect>
    implements CollectService {
  @Autowired private CollectMapper collectMapper;

  @Override
  public int addCollection(Collect collect) {
    CollectMapper baseMapper = this.baseMapper;
    return baseMapper.insert(collect);
  }

  @Override
  public boolean existSongId(Integer userId, Integer songId) {
    CollectMapper collectMapper = this.baseMapper;
    boolean exists =
        collectMapper.exists(
            new QueryWrapper<Collect>().eq("user_id", userId).eq("song_id", songId));
    return exists;
  }

  @Override
  public boolean updateCollectMsg(Collect collect) {
    return collectMapper.updateCollectMsg(collect) > 0 ? true : false;
  }

  @Override
  public int deleteCollect(Integer userId, Integer songId) {
    CollectMapper collectMapper = this.baseMapper;
    return collectMapper.delete(
        new QueryWrapper<Collect>().eq("user_id", userId).eq("song_id", songId));
  }

  @Override
  public List<Collect> allCollect() {

    return collectMapper.allCollect();
  }

  @Override
  public List<Collect> collectionOfUser(Integer userId) {
    CollectMapper collectMapper = this.baseMapper;
    return collectMapper.selectList(new QueryWrapper<Collect>().eq("user_id", userId));
  }
}
