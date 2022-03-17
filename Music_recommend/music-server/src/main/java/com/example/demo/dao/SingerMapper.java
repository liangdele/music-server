package com.example.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.domain.Singer;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SingerMapper extends BaseMapper<Singer> {


    int insert(Singer record);

    int insertSelective(Singer record);

    Singer selectByPrimaryKey(Integer id);


    int updateByPrimaryKey(Singer record);

    int updateSingerMsg(Singer record);

    int updateSingerPic(Singer record);

    int deleteSinger(Integer id);




}
