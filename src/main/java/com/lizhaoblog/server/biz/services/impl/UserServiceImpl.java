/*
 * Copyright (C), 2015-2018
 * FileName: UserServiceImpl
 * Author:   zhao
 * Date:     2018/7/5 9:53
 * Description: UserService的实现类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.lizhaoblog.server.biz.services.impl;

import com.google.gson.Gson;
import com.lizhaoblog.base.message.impl.ByteMessage;
import com.lizhaoblog.base.message.IMessage;
import com.lizhaoblog.base.message.impl.StringMessage;
import com.lizhaoblog.base.session.Session;
import com.lizhaoblog.base.session.SessionManager;
import com.lizhaoblog.base.util.GsonUtil;
import com.lizhaoblog.server.biz.constant.CommonValue;
import com.lizhaoblog.server.biz.dao.mysql.UserDao;
import com.lizhaoblog.server.biz.entity.User;
import com.lizhaoblog.server.biz.services.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br>
 * 〈UserService的实现类〉
 *
 * @author zhao
 * @date 2018/7/5 9:53
 * @since 1.0.0
 */
@Service
public class UserServiceImpl implements UserService {

  private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
  @Autowired
  private UserDao userDao;

  @Override
  public Long count() {
    return userDao.count();
  }

  @Override
  public List<User> listAll() {
    return userDao.listAll();
  }

  @Override
  public void insert(User user) {
    userDao.insert(user);
  }

  @Override
  public void doTest(IMessage message, Session session) {

    logger.info("服务器收到的数据内容：data=" + message);
    List<User> all = listAll();
    logger.info(all.toString());

    User user = new User();
    user.setName("a");
    user.setAccount("aa");
    user.setPassword("aaa");
    insert(user);

    all = listAll();
    logger.info(all.toString());

    String result = "";
    Map<String, Object> linkedHashMap = new LinkedHashMap<>();// 使用LinkedHashMap将结果按先进先出顺序排列
    linkedHashMap.put("key1", "value1");
    linkedHashMap.put("key2", "小李，我是服务器，我收到你的信息了。");
    linkedHashMap.put("key3", 3);
    Gson gson = GsonUtil.getGson();
    String str = gson.toJson(linkedHashMap);

    StringMessage stringMessage = new StringMessage(CommonValue.CM_MSG_TEST);
    stringMessage.setStatusCode(CommonValue.MSG_STATUS_CODE_SUCCESS);
    stringMessage.setBody(str);

//    SessionManager.getInstance().sendMessage(session, result);
    SessionManager.getInstance().sendMessage(session, stringMessage);



  }

  @Override
  public void doTestByte(IMessage message, Session session) {

    logger.info("服务器收到的doTestByte 数据内容：data=" + message);
    ByteMessage byteMessage = new ByteMessage();
    byteMessage.setMessageId(com.lizhaoblog.server.biz.constant.CommonValue.CM_MSG_TEST_BYTE);
    byteMessage.setStatusCode(com.lizhaoblog.server.biz.constant.CommonValue.MSG_STATUS_CODE_SUCCESS);
    byteMessage.addAttr(2);
    SessionManager.getInstance().sendMessage(session, byteMessage);


  }

}