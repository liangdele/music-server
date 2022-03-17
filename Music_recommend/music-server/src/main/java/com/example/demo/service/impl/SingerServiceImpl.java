package com.example.demo.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.dao.SingerMapper;
import com.example.demo.domain.Singer;
import com.example.demo.service.SingerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class SingerServiceImpl extends ServiceImpl<SingerMapper, Singer> implements SingerService {

  @Autowired private SingerMapper singerMapper;
@Autowired private  StringRedisTemplate redisTemplate;
  @Override
  public boolean updateSingerMsg(Singer singer) {
    return singerMapper.updateSingerMsg(singer) > 0 ? true : false;
  }

  @Override
  public boolean updateSingerPic(Singer singer) {

    return singerMapper.updateSingerPic(singer) > 0 ? true : false;
  }

  @Override
  public boolean deleteSinger(Integer id) {
    return singerMapper.deleteSinger(id) > 0 ? true : false;
  }
  @Cacheable(value = {"singer"},key = "#root.methodName")
  @Override
  public List<Singer> allSinger() {
//    //查redis
//    String singerJson = redisTemplate.opsForValue().get("singerJson");
//    if(!StringUtils.isEmpty(singerJson)){
//      List<Singer> singers = JSON.parseObject(singerJson, new TypeReference<List<Singer>>() {
//      });
//      return singers;
//    }
    //查数据库
    SingerMapper baseMapper = this.baseMapper;
    List<Singer> singers = baseMapper.selectList(null);
    String s = JSON.toJSONString(singers);
//    redisTemplate.opsForValue().set("singerJson",s,1, TimeUnit.DAYS);
    return singers;
  }

  @Override
  public boolean addSinger(Singer singer) {

    return singerMapper.insertSelective(singer) > 0 ? true : false;
  }

  @Override
  public List<Singer> singerOfName(String name) {
    SingerMapper baseMapper = this.baseMapper;
    return baseMapper.selectList(new QueryWrapper<Singer>().like("name",name));
  }

  @Override
  public List<Singer> singerOfSex(Integer sex) {
    List<Singer> singerSexList=null;
    String SingerSex = String.valueOf(sex);
    String singerSex = redisTemplate.opsForValue().get(SingerSex);
    redisTemplate.delete(SingerSex);
    if(!StringUtils.isEmpty(singerSex)){
      System.out.println("查询redis");
      singerSexList = JSON.parseObject(singerSex, new TypeReference<List<Singer>>() {
      });
      return singerSexList;
    }
    System.out.println("查询数据库");
    SingerMapper baseMapper = this.baseMapper;
    singerSexList = baseMapper.selectList(new QueryWrapper<Singer>().eq("sex", sex));
    String s = JSON.toJSONString(singerSexList);
    redisTemplate.opsForValue().set(SingerSex,s,1,TimeUnit.DAYS);
    return singerSexList;
  }
}
