package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.domain.Consumer;
import com.example.demo.exception.PhoneExistException;
import com.example.demo.exception.UserExistException;
import com.example.demo.vo.ConsumerVo;


import java.util.List;

public interface ConsumerService extends IService<Consumer> {

  void addUser(Consumer consumer);

  int  updateUserMsg(Consumer consumer);

  int updateUserAvator(Consumer consumer);

  boolean existUser(String username);

  Consumer veritypasswd(ConsumerVo vo);

  boolean deleteUser(Integer id);

  List<Consumer> allUser();

  Consumer userOfId(Integer id);

  List<Consumer> loginStatus(String username);

  void checkUserNameUnique(String username) throws UserExistException;

  void checkPhoneUnique(String phone) throws PhoneExistException;
}
