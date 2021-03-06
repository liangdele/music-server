package com.example.demo.service;

import com.example.demo.domain.Collect;

import java.util.List;

public interface CollectService {

    int addCollection(Collect collect);

    boolean existSongId(Integer userId, Integer songId);

    boolean updateCollectMsg(Collect collect);

    int deleteCollect(Integer userId, Integer songId);

    List<Collect> allCollect();

    List<Collect> collectionOfUser(Integer userId);
}
