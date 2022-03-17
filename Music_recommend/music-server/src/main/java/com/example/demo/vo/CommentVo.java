package com.example.demo.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/** @Author liangdele @Description: @Date: Created in 10:55 2022/3/16 */
@Data
public class CommentVo {
  @TableId(type = IdType.AUTO)
  private Integer userId;
  private Byte type;
  private Integer songListId;
  private Integer songId;
  private String content;
}
