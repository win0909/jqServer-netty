/*
 * Copyright (C), 2015-2018
 * FileName: INetworkConsumer
 * Author:   zhao
 * Date:     2018/6/23 21:06
 * Description: 网络消息处理器
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.lizhaoblog.server.core.customer;

import com.lizhaoblog.base.concurrent.commond.IHandler;
import com.lizhaoblog.base.message.StringMessage;
import com.lizhaoblog.base.network.processor.IProcessor;
import com.lizhaoblog.base.concurrent.dictionary.IMessageDictionary;
import com.lizhaoblog.base.constant.ConstantValue;
import com.lizhaoblog.base.network.customer.INetworkConsumer;
import com.lizhaoblog.base.session.Session;
import com.lizhaoblog.base.session.SessionManager;
import com.lizhaoblog.server.core.processor.LogicProcessor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import io.netty.channel.Channel;

/**
 * 〈一句话功能简述〉<br>
 * 〈网络消息处理器,实现类〉
 *
 * @author zhao
 * @date 2018/6/23 21:09
 * @since 1.0.0
 */
@Component
@Scope("singleton")
public class NetworkConsumer implements INetworkConsumer {
  private static final Logger logger = LoggerFactory.getLogger(NetworkConsumer.class);

  private Map<Integer, IProcessor> processors = new HashMap<>(10);

  @Autowired
  private IMessageDictionary messageDictionary;

  public void registerProcessor(int queueId, IProcessor processor) {
    processors.put(queueId, processor);
  }

  @PostConstruct
  public void init() {
    registerProcessor(ConstantValue.QUEUE_LOGIC, new LogicProcessor());
  }

  @Override
  public void consume(StringMessage message, Channel channel) {
    //获取session，后面需要根据session中的channel进行消息发送
    Session session = SessionManager.getInstance().getSessionByChannel(channel);
    if (session == null) {
      logger.debug("consume session is not found");
      return;
    }
    int messageId = message.getMessageId();
    logger.debug(messageId + "");

    IHandler<StringMessage, Session> handler = messageDictionary.getHandlerFromMessageId(messageId);
    handler.setMessage(message);
    handler.setParam(session);
    IProcessor processor = processors.get(ConstantValue.QUEUE_LOGIC);
    processor.process(handler);
  }
}