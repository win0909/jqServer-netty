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

import com.lizhaoblog.base.exception.ServerErrException;
import com.lizhaoblog.base.factory.ServerChannelFactory;
import com.lizhaoblog.base.network.IServer;
import com.lizhaoblog.server.pojo.ServerConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

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
  private static final Logger logger = LoggerFactory.getLogger(BasicServerImpl.class);

  Channel acceptorChannel;

  @Override
  public void start() {
    ChannelInitializer<SocketChannel> tcpServerStringInitializer = (ChannelInitializer<SocketChannel>) ServerConfig
            .getInstance().getApplicationContext().getBean("tcpServerStringInitializer");
    String channelType = ServerConfig.getInstance().getChannelType();
    Integer port = ServerConfig.getInstance().getPort();

    ServerConfig.getInstance().printServerInfo();
    try {
      acceptorChannel = ServerChannelFactory.createAcceptorChannel(channelType, tcpServerStringInitializer, port);
      acceptorChannel.closeFuture().sync();
    } catch (ServerErrException e) {
      logger.debug("服务启动错误", e);
    } catch (InterruptedException e) {
      logger.debug("服务启动错误", e);
    }

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