package com.example.demo.service;

import com.example.demo.domain.Rank;

public interface RankService {

    int rankOfSongListId(Long songListId);

    int addRank(Rank rank);
}
