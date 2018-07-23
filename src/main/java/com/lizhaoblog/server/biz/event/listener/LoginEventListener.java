/*
 * Copyright (C), 2015-2018
 * FileName: LoginEventListener
 * Author:   zhao
 * Date:     2018/7/20 14:57
 * Description: 登录事件
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.lizhaoblog.server.biz.event.listener;

import com.lizhaoblog.base.event.IEventListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 〈一句话功能简述〉<br>
 * 〈登录事件〉
 *
 * @author zhao
 * @date 2018/7/20 14:57
 * @since 1.0.1
 */
@Component
@Scope("singleton")
public class LoginEventListener implements IEventListener {
  public static final Logger logger = LoggerFactory.getLogger(LoginEventListener.class);

  @Override
  public void execute(Object param) {
    logger.info("LoginEventListener fire");
  }
}