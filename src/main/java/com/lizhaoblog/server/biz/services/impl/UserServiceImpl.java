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

import com.lizhaoblog.base.session.Session;
import com.lizhaoblog.base.session.SessionManager;
import com.lizhaoblog.server.biz.dao.mysql.UserDao;
import com.lizhaoblog.server.biz.entity.User;
import com.lizhaoblog.server.biz.services.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
  public void doTest(String message, Session session) {
    logger.info("服务器收到的数据内容：data=" + message);
    String result = "小李，我是服务器，我收到你的信息了。";
    SessionManager.getInstance().sendMessage(session, result);

    List<User> all = listAll();
    logger.info(all.toString());

    User user = new User();
    user.setName("a");
    user.setAccount("aa");
    user.setPassword("aaa");
    insert(user);

    all = listAll();
    logger.info(all.toString());
  }

}