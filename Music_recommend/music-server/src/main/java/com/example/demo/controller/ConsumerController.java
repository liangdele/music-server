package com.example.demo.controller;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.domain.Consumer;
import com.example.demo.exception.BizCodeEnume;
import com.example.demo.exception.PhoneExistException;
import com.example.demo.exception.UserExistException;
import com.example.demo.service.impl.ConsumerServiceImpl;
import com.example.demo.vo.ConsumerVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.Date;

@RestController
public class ConsumerController {

  @Autowired private ConsumerServiceImpl consumerService;

  @Configuration
  public class MyPicConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
      registry
          .addResourceHandler("/avatorImages/**")
          .addResourceLocations(
              "file:E:\\Users\\Lenovo\\Desktop\\Music_recommend\\Music_recommend\\music-server\\avatorImages\\");
    }
  }

  /*      添加用户*/
  @ResponseBody
  @PostMapping("/user/add")
  public Object addUser(@Valid Consumer con) {
    JSONObject jsonObject = new JSONObject();
    String username = con.getUsername();
    String password = con.getPassword();
    Byte sex = con.getSex();
    String phone_num = con.getPhoneNum();
    String email = con.getEmail();
    Date birth = con.getBirth();
    String introduction = con.getIntroduction();
    String location = con.getLocation();
    String avator = con.getAvator();
    if (username.equals("") || username == null) {
      jsonObject.put("code", 0);
      jsonObject.put("msg", "用户名或密码错误");
      return jsonObject;
    }
    Consumer consumer =
        getConsumer(
            username, password, sex, phone_num, email, birth, introduction, location, avator);

    try {
      consumerService.addUser(consumer);
    } catch (UserExistException e) {
      jsonObject.put("code", BizCodeEnume.USER_EXIST_EXCEPTION.getCode());
      jsonObject.put("msg", BizCodeEnume.USER_EXIST_EXCEPTION.getMsg());
      return jsonObject;
    } catch (PhoneExistException e) {
      jsonObject.put("code", BizCodeEnume.PHONE_EXIST_EXCEPTION.getCode());
      jsonObject.put("msg", BizCodeEnume.PHONE_EXIST_EXCEPTION.getMsg());
      return jsonObject;
    }
    jsonObject.put("code", 1);
    jsonObject.put("msg", "注册成功");
    return jsonObject;
  }

  private static Consumer getConsumer(String username, String password, Byte sex, String phone_num, String email, Date birth, String introduction, String location, String avator) {
    Consumer consumer = new Consumer();
    consumer.setUsername(username);
    consumer.setPassword(password);
    consumer.setSex(new Byte(sex));
    if (phone_num == "") {
      consumer.setPhoneNum(null);
    } else {
      consumer.setPhoneNum(phone_num);
    }
    if (email == "") {
      consumer.setEmail(null);
    } else {
      consumer.setEmail(email);
    }
    consumer.setBirth(birth);
    consumer.setIntroduction(introduction);
    consumer.setLocation(location);
    consumer.setAvator(avator);
    consumer.setCreateTime(new Date());
    consumer.setUpdateTime(new Date());
    return consumer;
  }

  //    判断是否登录成功
  @ResponseBody
  @PostMapping("/user/login/status")
  public Object loginStatus(ConsumerVo consumerVo, HttpSession session) {
    JSONObject jsonObject = new JSONObject();
    Consumer res = consumerService.veritypasswd(consumerVo);
    if (res != null) {
      jsonObject.put("code", 1);
      jsonObject.put("msg", "登录成功");
      jsonObject.put("userMsg", consumerService.loginStatus(consumerVo.getUsername()));
      session.setAttribute("username", consumerVo.getUsername());
      return jsonObject;
    } else {
      jsonObject.put("code", 0);
      jsonObject.put("msg", "用户名或密码错误");
      return jsonObject;
    }
  }

  //    返回所有用户
  @GetMapping("/user")
  public Object allUser() {
    return consumerService.allUser();
  }

  //    返回指定ID的用户
  @GetMapping(value = "/user/detail")
  public Consumer userOfId(Integer id) {
    return consumerService.userOfId(id);
  }

  //    删除用户
  @GetMapping(value = "/user/delete")
  public Object deleteUser(HttpServletRequest req) {
    String id = req.getParameter("id");
    return consumerService.deleteUser(Integer.parseInt(id));
  }

  //    更新用户信息
  @ResponseBody
  @PostMapping( "/user/update")
  public Object updateUserMsg(Consumer vo) {
    JSONObject jsonObject = new JSONObject();
    Integer id = vo.getId();
    String username = vo.getUsername();
    String password = vo.getPassword();
    byte sex = vo.getSex();
    String phoneNum = vo.getPhoneNum();
    String email = vo.getEmail();
    Date birth = vo.getBirth();
    String introduction = vo.getIntroduction();
    String location = vo.getLocation();
    if (vo.getUsername().equals("") || vo.getUsername() == null) {
      jsonObject.put("code", 0);
      jsonObject.put("msg", "用户名或密码错误");
      return jsonObject;
    }
    Consumer consumer = getConsumer(id,username, password, sex, phoneNum, email, birth, introduction, location);
    try{
       consumerService.updateUserMsg(consumer);
    }catch (UserExistException e) {
      jsonObject.put("code", BizCodeEnume.USER_EXIST_EXCEPTION.getCode());
      jsonObject.put("msg", BizCodeEnume.USER_EXIST_EXCEPTION.getMsg());
      return jsonObject;
    } catch (PhoneExistException e) {
      jsonObject.put("code", BizCodeEnume.PHONE_EXIST_EXCEPTION.getCode());
      jsonObject.put("msg", BizCodeEnume.PHONE_EXIST_EXCEPTION.getMsg());
      return jsonObject;
    }
    jsonObject.put("code", 1);
    jsonObject.put("msg", "修改成功");
    return jsonObject;
  }

  private static Consumer getConsumer(
          Integer id, String username, String password, Byte sex, String phoneNum, String email, Date birth, String introduction, String location) {
    Consumer updateVo = new Consumer();
    updateVo.setId(id);
    updateVo.setUsername(username);
    updateVo.setPassword(password);
    updateVo.setSex(new Byte(sex));
    if (phoneNum == "") {
      updateVo.setPhoneNum(null);
    } else {
      updateVo.setPhoneNum(phoneNum);
    }

    if (email == "") {
      updateVo.setEmail(null);
    } else {
      updateVo.setEmail(email);
    }
    updateVo.setBirth(birth);
    updateVo.setIntroduction(introduction);
    updateVo.setLocation(location);
    updateVo.setUpdateTime(new Date());
    return updateVo;
  }

  //    更新用户头像
  @ResponseBody
  @PostMapping("/user/avatar/update")
  public Object updateUserPic(
      @RequestParam("file") MultipartFile avatorFile, @RequestParam("id") int id) {
    JSONObject jsonObject = new JSONObject();

    if (avatorFile.isEmpty()) {
      jsonObject.put("code", 0);
      jsonObject.put("msg", "文件上传失败！");
      return jsonObject;
    }
    String fileName = System.currentTimeMillis() + avatorFile.getOriginalFilename();
    String filePath =
        System.getProperty("user.dir") + System.getProperty("file.separator") + "avatorImages";
    File file1 = new File(filePath);
    if (!file1.exists()) {
      file1.mkdir();
    }

    File dest = new File(filePath + System.getProperty("file.separator") + fileName);
    String storeAvatorPath = "/avatorImages/" + fileName;
    try {
      avatorFile.transferTo(dest);
      Consumer consumer = new Consumer();
      consumer.setId(id);
      consumer.setAvator(storeAvatorPath);
      int res = consumerService.updateUserAvator(consumer);
      if (res!=0) {
        jsonObject.put("code", 1);
        jsonObject.put("avator", storeAvatorPath);
        jsonObject.put("msg", "上传成功");
        return jsonObject;
      } else {
        jsonObject.put("code", 0);
        jsonObject.put("msg", "上传失败");
        return jsonObject;
      }
    } catch (IOException e) {
      jsonObject.put("code", 0);
      jsonObject.put("msg", "上传失败" + e.getMessage());
      return jsonObject;
    } finally {
      return jsonObject;
    }
  }
}
