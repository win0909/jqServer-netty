/*
 * Copyright (C), 2015-2018
 * FileName: TestFirstHandler
 * Author:   zhao
 * Date:     2018/6/25 16:24
 * Description: 用于测试的第一个handler
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.lizhaoblog.server.biz.handler;

import com.lizhaoblog.base.concurrent.handler.AbstractHandler;
import com.lizhaoblog.base.session.Session;
import com.lizhaoblog.base.session.SessionManager;
import com.lizhaoblog.server.biz.dao.mysql.UserDao;
import com.lizhaoblog.server.biz.entity.User;
import com.lizhaoblog.server.biz.services.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈用于测试的第一个handler〉
 *
 * @author zhao
 * @date 2018/6/25 16:24
 * @since 1.0.0
 */
@Component
public class TestFirstHandler extends AbstractHandler<String, Session> {
  @Autowired
  private UserService userService;

  @Override
  public void doAction() {
    userService.doTest(message,param);
  }
}