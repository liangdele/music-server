package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.domain.Collect;
import com.example.demo.service.impl.CollectServiceImpl;
import com.example.demo.vo.CollectionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
@Controller
public class CollectController {

  @Autowired private CollectServiceImpl collectService;

  //    添加收藏的歌曲
  @ResponseBody
  @PostMapping("/collection/add")
  public Object addCollection(CollectionVo collectVo) {
    JSONObject jsonObject = new JSONObject();
    String songId = collectVo.getSongId();
    String userId = collectVo.getUserId();
    String type = collectVo.getType();
    String songListId = collectVo.getSongListId();
    if (songId == "") {
      jsonObject.put("code", 0);
      jsonObject.put("msg", "收藏歌曲为空");
      return jsonObject;
    } else if (collectService.existSongId(Integer.parseInt(userId), Integer.parseInt(songId))) {
      jsonObject.put("code", 2);
      jsonObject.put("msg", "取消收藏");
      collectService.deleteCollect(Integer.parseInt(userId), Integer.parseInt(songId));
      return jsonObject;
    }
    Collect collect = new Collect();
    collect.setUserId(Integer.parseInt(userId));
    collect.setType(new Byte(type));
    if (new Byte(type) == 0) {
      collect.setSongId(Integer.parseInt(songId));
    } else if (new Byte(type) == 1) {
      collect.setSongListId(Integer.parseInt(songListId));
    }
    collect.setCreateTime(new Date());
    int res = collectService.addCollection(collect);
    if (res!=0) {
      jsonObject.put("code", 1);
      jsonObject.put("msg", "收藏成功");
      return jsonObject;
    } else {
      jsonObject.put("code", 0);
      jsonObject.put("msg", "收藏失败");
      return jsonObject;
    }
  }

  //    返回所有用户收藏列表
  @RequestMapping(value = "/collection", method = RequestMethod.GET)
  public Object allCollection() {
    return collectService.allCollect();
  }

  //    返回的指定用户ID收藏列表
  @GetMapping(value = "/collection/detail")
  public Object collectionOfUser(String userId) {
    return collectService.collectionOfUser(Integer.parseInt(userId));
  }

  //    删除收藏的歌曲
  @RequestMapping(value = "/collection/delete", method = RequestMethod.GET)
  public Object deleteCollection(HttpServletRequest req) {
    String user_id = req.getParameter("userId").trim();
    String song_id = req.getParameter("songId").trim();
    return collectService.deleteCollect(Integer.parseInt(user_id), Integer.parseInt(song_id));
  }

  //    更新收藏
  @ResponseBody
  @RequestMapping(value = "/collection/update", method = RequestMethod.POST)
  public Object updateCollectMsg(HttpServletRequest req) {
    JSONObject jsonObject = new JSONObject();
    String id = req.getParameter("id").trim();
    String user_id = req.getParameter("userId").trim();
    String type = req.getParameter("type").trim();
    String song_id = req.getParameter("songId").trim();
    //        String song_list_id = req.getParameter("songListId").trim();

    Collect collect = new Collect();
    collect.setId(Integer.parseInt(id));
    collect.setUserId(Integer.parseInt(user_id));
    collect.setType(new Byte(type));
    collect.setSongId(Integer.parseInt(song_id));

    boolean res = collectService.updateCollectMsg(collect);
    if (res) {
      jsonObject.put("code", 1);
      jsonObject.put("msg", "修改成功");
      return jsonObject;
    } else {
      jsonObject.put("code", 0);
      jsonObject.put("msg", "修改失败");
      return jsonObject;
    }
  }
}
