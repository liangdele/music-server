package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.domain.Rank;
import com.example.demo.service.impl.RankServiceImpl;
import com.example.demo.vo.RankVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@Controller
public class RankController {

    @Autowired
    private RankServiceImpl rankService;

//    提交评分
    @ResponseBody
    @PostMapping(value = "/rank/add")
    public Object addRank(RankVo rankVo){
        JSONObject jsonObject = new JSONObject();
        Long consumerId = rankVo.getConsumerId();
        Long songListId = rankVo.getSongListId();
        Integer score = rankVo.getScore();
        Rank rank = new Rank();
        rank.setSongListId(consumerId);
        rank.setConsumerId(songListId);
        rank.setScore(score);
        int res = rankService.addRank(rank);
        if (res!=0){
            jsonObject.put("code", 1);
            jsonObject.put("msg", "评价成功");
            return jsonObject;
        }else {
            jsonObject.put("code", 0);
            jsonObject.put("msg", "评价失败");
            return jsonObject;
        }
    }

//    获取指定歌单的评分
    @GetMapping( "/rank")
    public int rankOfSongListId(String songListId){
        return rankService.rankOfSongListId(Long.parseLong(songListId));
    }
}
