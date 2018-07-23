/*
 * Copyright (C), 2015-2018
 * FileName: TcpMessageStringHandler
 * Author:   zhao
 * Date:     2018/6/12 15:01
 * Description: tcp消息处理
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.lizhaoblog.server.channel.tcp.str;

import com.lizhaoblog.base.message.IMessage;
import com.lizhaoblog.base.network.customer.INetworkConsumer;
import com.lizhaoblog.base.network.listener.INetworkEventListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 〈一句话功能简述〉<br>
 * 〈tcp消息处理〉
 *
 * @author zhao
 * @date 2018/6/12 15:01
 * @since 1.0.0
 */
@Component
@Scope("prototype")
public class TcpMessageStringHandler extends SimpleChannelInboundHandler<IMessage> {

  @Autowired
  private INetworkEventListener listener;
  @Autowired
  private INetworkConsumer consumer;

  @Override
  protected void channelRead0(ChannelHandlerContext ctx, IMessage msg) {
    consumer.consume(msg, ctx.channel());
  }

  @Override
  public void channelActive(ChannelHandlerContext ctx) {
    listener.onConnected(ctx);
  }

  @Override
  public void channelInactive(ChannelHandlerContext ctx) {
    listener.onDisconnected(ctx);
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable throwable) {
    listener.onExceptionCaught(ctx, throwable);
  }
}