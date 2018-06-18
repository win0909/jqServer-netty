/**
 * Copyright (C), 2015-2018
 * FileName: ServerChannelFactory
 * Author:   zhao
 * Date:     2018/6/12 14:29
 * Description: ServerChannel的工厂类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.lizhaoblog.base.factory;

import com.lizhaoblog.base.constant.ConstantValue;
import com.lizhaoblog.base.exception.ServerErrException;
import com.lizhaoblog.server.channel.tcp.str.TcpServerStringInitializer;
import com.lizhaoblog.server.pojo.ServerConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

/**
 * 〈一句话功能简述〉<br>
 * 〈ServerChannel的工厂类〉
 *
 * @author zhao
 * @date 2018/6/12 14:29
 * @since 1.0.0
 */
public class ServerChannelFactory {
  private static final Logger logger = LoggerFactory.getLogger(ServerChannelFactory.class);

  public static Channel createAcceptorChannel() throws ServerErrException {
    Integer port = ServerConfig.getInstance().getPort();
    final ServerBootstrap serverBootstrap = ServerBootstrapFactory.createServerBootstrap();
    serverBootstrap.childHandler(getChildHandler());
    //        serverBootstrap.childHandler()
    logger.info("创建Server...");
    try {
      ChannelFuture channelFuture = serverBootstrap.bind(port).sync();
      channelFuture.awaitUninterruptibly();
      if (channelFuture.isSuccess()) {
        return channelFuture.channel();
      } else {
        String errMsg = "Failed to open socket! Cannot bind to port: " + port + "!";
        logger.error(errMsg);
        throw new ServerErrException(errMsg);
      }
      //      java.net.BindException: Address already in use: bind
    } catch (Exception e) {
      logger.debug(port + "is bind");
      throw new ServerErrException(e);
    }
  }

  private static ChannelInitializer<SocketChannel> getChildHandler() throws ServerErrException {

    String protocolType = ServerConfig.getInstance().getProtocolType();
    if (ConstantValue.PROTOCOL_TYPE_HTTP.equals(protocolType) || ConstantValue.PROTOCOL_TYPE_HTTPS
            .equals(protocolType)) {
    } else if (ConstantValue.PROTOCOL_TYPE_TCP.equals(protocolType)) {
      return new TcpServerStringInitializer();
    } else if (ConstantValue.PROTOCOL_TYPE_WEBSOCKET.equals(protocolType)) {
    } else if (ConstantValue.PROTOCOL_TYPE_CUSTOM.equals(protocolType)) {
    } else {
    }
    String errMsg = "undefined protocol:" + protocolType + "!";
    throw new ServerErrException(errMsg);
  }
}