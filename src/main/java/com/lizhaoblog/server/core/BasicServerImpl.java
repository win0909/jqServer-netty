/*
 * Copyright (C), 2015-2018
 * FileName: BasicServerImpl
 * Author:   zhao
 * Date:     2018/6/20 20:23
 * Description: BasicServerImpl
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.lizhaoblog.server.core;

import com.lizhaoblog.base.factory.ServerChannelFactory;
import com.lizhaoblog.base.network.IServer;

import org.springframework.stereotype.Component;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFutureListener;

/**
 * 〈一句话功能简述〉<br>
 * 〈BasicServerImpl〉
 *
 * @author zhao
 * @date 2018/6/20 20:23
 * @since 1.0.0
 */
@Component
public class BasicServerImpl implements IServer {
  Channel acceptorChannel;

  @Override
  public void start() throws Exception {
    acceptorChannel = ServerChannelFactory.createAcceptorChannel();
    acceptorChannel.closeFuture().sync();
  }

  @Override
  public void stop() throws Exception {
    if (acceptorChannel != null) {
      acceptorChannel.close().addListener(ChannelFutureListener.CLOSE);
    }
  }

  @Override
  public void restart() throws Exception {
    stop();
    start();
  }
}