package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.dao.RankMapper;
import com.example.demo.domain.Rank;
import com.example.demo.service.RankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RankServiceImpl extends ServiceImpl<RankMapper,Rank>implements RankService {

    @Autowired
    private RankMapper rankMapper;

    @Override
    public int rankOfSongListId(Long songListId) {
        return rankMapper.selectScoreSum(songListId) / rankMapper.selectRankNum(songListId);
    }

    @Override
    public int addRank(Rank rank) {
        RankMapper baseMapper = this.baseMapper;
        int insert = baseMapper.insert(rank);
        return insert;
    }
}
