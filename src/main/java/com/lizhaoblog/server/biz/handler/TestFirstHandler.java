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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class TestFirstHandler extends AbstractHandler<String, Session> {

  private static final Logger logger = LoggerFactory.getLogger(TestFirstHandler.class);

  @Override
  public void doAction() {
    //    logger.info("TestFirstHandler doAction " + this);
    logger.info("服务器收到的数据内容：data=" + message);
    String result = "小李，我是服务器，我收到你的信息了。";
    SessionManager.getInstance().sendMessage(param, result);
  }
}