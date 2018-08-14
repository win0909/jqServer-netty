/*
 * Copyright (C), 2015-2018
 * FileName: EasyWsServer
 * Author:   zhao
 * Date:     2018/8/14 10:43
 * Description: 简单的WebScoket服务器
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.lizhaoblog.demopro.websocket.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * 〈一句话功能简述〉<br>
 * 〈简单的WebScoket服务器〉
 *
 * @author zhao
 * @date 2018/8/14 10:43
 * @since 1.0.1
 */
public class EasyWsServer {
  private int port;

  public EasyWsServer(int port) {
    this.port = port;
  }

  public void start() throws InterruptedException {
    ServerBootstrap serverBootstrap = new ServerBootstrap();
    EventLoopGroup bossGroup = new NioEventLoopGroup();
    EventLoopGroup workerGroup = new NioEventLoopGroup();
    serverBootstrap.group(bossGroup, workerGroup);
    serverBootstrap.channel(NioServerSocketChannel.class);
    serverBootstrap.childHandler(new ChannelInitializer() {
      @Override
      protected void initChannel(Channel channel) throws Exception {
        ChannelPipeline pipeline = channel.pipeline();
        pipeline.addLast("http-codec", new HttpServerCodec()); // Http消息编码解码
        pipeline.addLast("aggregator", new HttpObjectAggregator(65536)); // Http消息组装
        pipeline.addLast("http-chunked", new ChunkedWriteHandler()); // WebSocket通信支持
        pipeline.addLast(new EasyWsServerHandler());
      }
    });

    // 监听端口
    ChannelFuture channelFuture = serverBootstrap.bind(port).sync();
    channelFuture.awaitUninterruptibly();
    // 堵塞线程，保持长连接
    channelFuture.channel().closeFuture().sync();
  }

}
