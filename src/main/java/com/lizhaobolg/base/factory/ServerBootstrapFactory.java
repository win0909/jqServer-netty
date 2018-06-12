/**
 * Copyright (C), 2015-2018
 * FileName: ServerBootstrapFactory
 * Author:   zhao
 * Date:     2018/6/12 10:56
 * Description: Bootstrap工厂类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.lizhaobolg.base.factory;

import com.lizhaobolg.base.constant.ConstantValue;
import com.lizhaobolg.server.pojo.ServerConfig;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.oio.OioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.oio.OioServerSocketChannel;

/**
 * 〈一句话功能简述〉<br>
 * 〈Bootstrap工厂类〉
 *
 * @author zhao
 * @date 2018/6/12
 * @since 1.0.0
 */
public class ServerBootstrapFactory {
  private ServerBootstrapFactory() {
  }

  public static ServerBootstrap createServerBootstrap() throws UnsupportedOperationException {

    ServerBootstrap serverBootstrap = new ServerBootstrap();
    switch (ServerConfig.getInstance().getChannelType()) {
      case ConstantValue.CHANNEL_TYPE_NIO:
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        serverBootstrap.group(bossGroup, workerGroup);
//        ServerConfig.setBossGroup(bossGroup);
//        ServerConfig.setWorkerGroup(workerGroup);
        serverBootstrap.channel(NioServerSocketChannel.class);

        return serverBootstrap;
      case ConstantValue.CHANNEL_TYPE_OIO:
        serverBootstrap.group(new OioEventLoopGroup());
        serverBootstrap.channel(OioServerSocketChannel.class);

        return serverBootstrap;
      default:
        throw new UnsupportedOperationException(
                "Failed to create ServerBootstrap,  " +ServerConfig.getInstance().getChannelType() + " not supported!");
    }
  }
}