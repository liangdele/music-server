package com.example.demo.service.impl;

import com.example.demo.recommend.dto.RelateDTO;
import com.example.demo.service.RecommendSongListService;
import junit.framework.TestCase;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author root
 * @Description:
 * @Package com.example.demo.service.impl
 * @date 2021/5/6 19:31
 */
public class RecommendServiceImplTest extends TestCase {
    @Autowired
    private RecommendSongListService recommendSongListService;

    public void testGetRankData() {
        List<RelateDTO> rankData = recommendSongListService.getRankData();
        for (RelateDTO rankDatum : rankData) {
            System.out.println(rankDatum);
        }
    }

    public void testGetSongListData() {
    }

    public void testRecommendSongList() {
    }
}