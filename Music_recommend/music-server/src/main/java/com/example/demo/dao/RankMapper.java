package com.example.demo.dao;

import com.example.demo.domain.Rank;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RankMapper {
    /**
     * 插入评分记录
     * @param record
     * @return
     */
    int insert(Rank record);

    /**
     * 插入评分记录（不为空）
     * @param record
     * @return
     */
    int insertSelective(Rank record);

    /**
     * 查总分
     * @param songListId
     * @return
     */
    int selectScoreSum(Long songListId);

    /**
     * 查总评分人数
     * @param songListId
     * @return
     */
    int selectRankNum(Long songListId);

    /**
     * 查询所有评分记录
     * @return
     */
    List<Rank> selectRanks();

}
