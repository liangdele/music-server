package com.example.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.domain.Collect;
import javafx.scene.chart.BarChart;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CollectMapper  extends BaseMapper<Collect> {

    int insertSelective(Collect record);

    Collect selectByPrimaryKey(Integer id);


    int updateByPrimaryKey(Collect record);


    int updateCollectMsg(Collect collect);

    int deleteCollect(@Param("userId") Integer userId, @Param("songId") Integer songId);

    List<Collect> allCollect();

}
