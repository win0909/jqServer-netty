/*
 * Copyright (C), 2015-2018
 * FileName: EventRegister
 * Author:   zhao
 * Date:     2018/7/20 11:28
 * Description: 事件注册
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.lizhaoblog.server.biz.event;

import com.lizhaoblog.base.event.EventUtil;
import com.lizhaoblog.server.biz.constant.CommonValue;
import com.lizhaoblog.server.biz.event.listener.LoginEventListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 〈一句话功能简述〉<br>
 * 〈事件注册〉
 *
 * @author zhao
 * @date 2018/7/20 11:28
 * @since 1.0.1
 */
@Component
@Scope("singleton")
public class EventRegister {

  @Autowired
  private LoginEventListener loginEventListener;

  @PostConstruct
  public void registerPreparedListeners() {
    addLoginListener();
  }

  /**
   * 登录事件监听器
   */
  private void addLoginListener() {
    EventUtil.addListener(CommonValue.EVENT_TYPE_LOGIN, loginEventListener);
  }

}
