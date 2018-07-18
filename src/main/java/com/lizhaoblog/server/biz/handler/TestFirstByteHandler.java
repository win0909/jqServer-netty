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
import com.lizhaoblog.base.message.IMessage;
import com.lizhaoblog.base.session.Session;
import com.lizhaoblog.server.biz.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 〈一句话功能简述〉<br>
 * 〈用于测试的第一个handler〉
 *
 * @author zhao
 * @date 2018/6/25 16:24
 * @since 1.0.0
 */
@Component
public class TestFirstByteHandler extends AbstractHandler<IMessage, Session> {
  @Autowired
  private UserService userService;

  @Override
  public void doAction() {
    userService.doTestByte(message,param);
  }
}