/*
 * Copyright (C), 2015-2018
 * FileName: NetworkListener
 * Author:   zhao
 * Date:     2018/6/22 10:52
 * Description: 网络事件监听器，实现类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.lizhaoblog.server.core.listener;

import com.lizhaoblog.base.network.listener.INetworkEventListener;
import com.lizhaoblog.base.session.SessionManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import io.netty.channel.ChannelHandlerContext;

/**
 * 〈一句话功能简述〉<br>
 * 〈网络事件监听器，实现类〉
 *
 * @author zhao
 * @date 2018/6/22 10:52
 * @since 1.0.0
 */
@Component
@Scope("singleton")
public class NetworkListener implements INetworkEventListener {

  private static final Logger logger = LoggerFactory.getLogger(NetworkListener.class);

  @Override
  public void onConnected(ChannelHandlerContext ctx) {
    logger.info("建立连接");
    SessionManager.getInstance().create(ctx.channel());
  }

  @Override
  public void onDisconnected(ChannelHandlerContext ctx) {
    logger.info("连接断开");
    SessionManager.getInstance().close(ctx.channel());
  }

  @Override
  public void onExceptionCaught(ChannelHandlerContext ctx, Throwable throwable) {
    logger.debug("异常发生", throwable);
  }
}