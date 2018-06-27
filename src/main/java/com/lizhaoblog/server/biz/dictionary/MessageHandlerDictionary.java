/*
 * Copyright (C), 2015-2018
 * FileName: MessageHandlerDictionary
 * Author:   zhao
 * Date:     2018/6/25 16:39
 * Description: 消息字典绑定
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.lizhaoblog.server.biz.dictionary;

import com.lizhaoblog.base.concurrent.commond.IHandler;
import com.lizhaoblog.base.concurrent.dictionary.IMessageDictionary;
import com.lizhaoblog.base.util.StringUtil;
import com.lizhaoblog.server.biz.constant.CommonValue;
import com.lizhaoblog.server.biz.handler.TestFirstHandler;
import com.lizhaoblog.server.pojo.ServerConfig;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

/**
 * 〈一句话功能简述〉<br>
 * 〈消息字典绑定〉
 *
 * @author zhao
 * @date 2018/6/25 16:39
 * @since 1.0.0
 */
@Component
@Scope("singleton")
public class MessageHandlerDictionary implements IMessageDictionary {
  private final Map<Integer, Class<? extends IHandler>> idHandleMap = new HashMap<>(10);

  @PostConstruct
  public void init() {
    register(CommonValue.CM_MSG_TEST, TestFirstHandler.class);
  }

  @Override
  public void register(int messageId, Class<? extends IHandler> handler) {
    idHandleMap.put(messageId, handler);
  }

  @Override
  public IHandler getHandlerFromMessageId(Integer messageId) {
    Class<? extends IHandler> clazz = idHandleMap.get(messageId);
    if (clazz != null) {
      try {
        String clazzName = StringUtil.toLowerCaseFirstOne(clazz.getSimpleName());
        return (IHandler) ServerConfig.getInstance().getApplicationContext().getBean(clazzName);
      } catch (Exception e) {
        return null;
      }
    }
    return null;
  }
}