/*
 * Copyright (C), 2015-2018
 * FileName: EasyWsClient
 * Author:   zhao
 * Date:     2018/8/14 10:23
 * Description: 最简单的websocket客户端
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.lizhaoblog.demopro.websocket.client;

import java.net.URI;
import java.net.URISyntaxException;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshakerFactory;
import io.netty.handler.codec.http.websocketx.WebSocketVersion;

/**
 * 〈一句话功能简述〉<br>
 * 〈最简单的websocket客户端〉
 *
 * @author zhao
 * @date 2018/8/14 10:23
 * @since 1.0.1
 */
public class EasyWsClient {
  private static EventLoopGroup group = new NioEventLoopGroup();
  //  private static final String ip = "127.0.0.1";
  //  private static final int port = 8088;

  private String ip;
  private int port;
  private String uriStr;
  private static EasyWsClientHandler handler;

  public EasyWsClient(String ip, int port) {
    this.ip = ip;
    this.port = port;
    uriStr = "ws//" + ip + ":" + port;
  }

  public void run() throws InterruptedException, URISyntaxException {
    // 主要是为handler(自己写的类)服务，用于初始化EasyWsHandle
    URI wsUri = new URI(uriStr);
    WebSocketClientHandshaker webSocketClientHandshaker = WebSocketClientHandshakerFactory
            .newHandshaker(wsUri, WebSocketVersion.V13, null, true, new DefaultHttpHeaders(), 100 * 1024 * 1024);
    handler = new EasyWsClientHandler(webSocketClientHandshaker);

    // 设置Bootstrap
    Bootstrap bootstrap = new Bootstrap();
    bootstrap.group(group);
    bootstrap.channel(NioSocketChannel.class);

    bootstrap.handler(new ChannelInitializer() {
      @Override
      protected void initChannel(Channel ch) {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new HttpClientCodec());
        pipeline.addLast(new HttpObjectAggregator(65536));
        pipeline.addLast(handler);
      }
    });

    // 连接服务端
    ChannelFuture channelFuture = bootstrap.connect(ip, port).sync();
    handler.handshakeFuture().sync();

    // 传输文本
    TextWebSocketFrame frame = new TextWebSocketFrame("hello");
    channelFuture.channel().writeAndFlush(frame);
    // 传输二进制字节数据
    byte[] bytes = new byte[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
    BinaryWebSocketFrame byteFrame = new BinaryWebSocketFrame(Unpooled.copiedBuffer(bytes));
    channelFuture.channel().writeAndFlush(byteFrame);

    // 堵塞线程，保持长连接
    channelFuture.channel().closeFuture().sync();
  }

}
