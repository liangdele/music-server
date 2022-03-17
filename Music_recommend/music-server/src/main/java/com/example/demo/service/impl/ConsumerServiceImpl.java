package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.dao.ConsumerMapper;
import com.example.demo.domain.Consumer;
import com.example.demo.exception.PhoneExistException;
import com.example.demo.exception.UserExistException;
import com.example.demo.service.ConsumerService;
import com.example.demo.vo.ConsumerVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

@Service
public class ConsumerServiceImpl extends ServiceImpl<ConsumerMapper, Consumer>
    implements ConsumerService {

  @Autowired private ConsumerMapper consumerMapper;

  @Override
  public void addUser(Consumer con) {
    //        return consumerMapper.insertSelective(consumer) >0 ?true:false;
    ConsumerMapper consumerMapper = this.baseMapper;
    checkPhoneUnique(con.getPhoneNum());
    checkUserNameUnique(con.getUsername());
    Consumer consumer = new Consumer();
    consumer.setUsername(con.getUsername());
    consumer.setBirth(con.getBirth());
    consumer.setSex(con.getSex());
    consumer.setPhoneNum(con.getPhoneNum());
    consumer.setEmail(con.getEmail());
    consumer.setLocation(con.getLocation());
    consumer.setIntroduction(con.getIntroduction());
    consumer.setAvator(con.getAvator());
    consumer.setCreateTime(new Date());
    consumer.setUpdateTime(new Date());
    // 密码明文加密
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    String encode = passwordEncoder.encode(con.getPassword());
    consumer.setPassword(encode);
    consumerMapper.insert(consumer);
  }

  @Override
  public void checkUserNameUnique(String username) throws UserExistException {
    ConsumerMapper baseMapper = this.baseMapper;
    Long usernameCount =
        baseMapper.selectCount(new QueryWrapper<Consumer>().eq("username", username));
    if (usernameCount > 0) {
      throw new UserExistException();
    }
  }

  @Override
  public void checkPhoneUnique(String phone) throws PhoneExistException {
    ConsumerMapper baseMapper = this.baseMapper;
    Long moblie = baseMapper.selectCount(new QueryWrapper<Consumer>().eq("phone_num", phone));
    if (moblie > 0) {
      throw new PhoneExistException();
    }
  }

  @Override
  public int updateUserMsg(Consumer vo) {
    ConsumerMapper consumerMapper = this.baseMapper;
    checkPhoneUnique(vo.getPhoneNum());
    checkUserNameUnique(vo.getUsername());
    // 密码明文加密
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    vo.setPassword(passwordEncoder.encode(vo.getPassword()));
    Integer id = vo.getId();
    return consumerMapper.update(vo, new QueryWrapper<Consumer>().eq("id", id));
  }

  @Override
  public int updateUserAvator(Consumer consumer) {
    ConsumerMapper consumerMapper = this.baseMapper;
    Integer id = consumer.getId();
    return consumerMapper.update(consumer, new QueryWrapper<Consumer>().eq("id", id));
  }

  @Override
  public boolean existUser(String username) {
    return consumerMapper.existUsername(username) > 0 ? true : false;
  }

  @Override
  public Consumer veritypasswd(ConsumerVo vo) {
    String username = vo.getUsername();
    String password = vo.getPassword();
    // 数据库查询是否存在
    ConsumerMapper baseMapper = this.baseMapper;
    Consumer username1 =
        baseMapper.selectOne(new QueryWrapper<Consumer>().eq("username", username));
    if (username1 == null) {
      // 登录失败
      return null;
    } else {
      String password1 = username1.getPassword();
      BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
      // 密码匹配
      boolean matches = bCryptPasswordEncoder.matches(password, password1);
      if (matches) {
        return username1;
      } else {
        return null;
      }
    }
  }

  //    删除用户
  @Override
  public boolean deleteUser(Integer id) {
    return consumerMapper.deleteUser(id) > 0 ? true : false;
  }

  @Override
  public List<Consumer> allUser() {
    return consumerMapper.allUser();
  }

  @Override
  public Consumer userOfId(Integer id) {
    ConsumerMapper baseMapper = this.baseMapper;
    Consumer consumer = baseMapper.selectById(id);
    return consumer;
  }

  @Override
  public List<Consumer> loginStatus(String username) {

    return consumerMapper.loginStatus(username);
  }
}
