/*
 * Copyright (C), 2015-2018
 * FileName: WebSocketClientHandlerTest
 * Author:   zhao
 * Date:     2018/8/2 17:32
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.lizhaoblog.server.channel.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.websocketx.CloseWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PongWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.util.CharsetUtil;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author zhao
 * @date 2018/8/2 17:32
 * @since 1.0.1
 */
public class WebSocketClientHandlerTest extends SimpleChannelInboundHandler<Object> {
  private static final Logger logger = LoggerFactory.getLogger(WebSocketClientHandlerTest.class);

  private final WebSocketClientHandshaker handshaker;
  private ChannelPromise handshakeFuture;

  public WebSocketClientHandlerTest(WebSocketClientHandshaker handshaker) {
    this.handshaker = handshaker;
  }

  public ChannelFuture handshakeFuture() {
    return handshakeFuture;
  }

  @Override
  public void handlerAdded(ChannelHandlerContext ctx) {
    handshakeFuture = ctx.newPromise();
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable arg1) {
    logger.info("异常发生", arg1);
  }

  @Override
  protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
//    logger.info("数据内容：data=" + data);

    Channel ch = ctx.channel();
    if (!handshaker.isHandshakeComplete()) {
      handshaker.finishHandshake(ch, (FullHttpResponse) msg);
      System.out.println("WebSocket Client connected!");
      handshakeFuture.setSuccess();
      return;
    }

    if (msg instanceof FullHttpResponse) {
      FullHttpResponse response = (FullHttpResponse) msg;
      throw new IllegalStateException(
              "Unexpected FullHttpResponse (getStatus=" + response.getStatus() +
                      ", content=" + response.content().toString(CharsetUtil.UTF_8) + ')');
    }

    WebSocketFrame frame = (WebSocketFrame) msg;
    if (frame instanceof TextWebSocketFrame) {
      TextWebSocketFrame textFrame = (TextWebSocketFrame) frame;
      System.out.println("WebSocket Client received message: " + textFrame.text());
    } else if (frame instanceof PongWebSocketFrame) {
      System.out.println("WebSocket Client received pong");
    } else if (frame instanceof CloseWebSocketFrame) {
      System.out.println("WebSocket Client received closing");
      ch.close();
    }


    //关闭链路
    //    ctx.close();
    //    channelInactive(ctx);
  }

  @Override
  public void channelActive(ChannelHandlerContext ctx) throws Exception {
    logger.info("客户端连接建立");
    // 在通道连接成功后发送握手连接
    handshaker.handshake(ctx.channel());
    super.channelActive(ctx);
  }

  @Override
  public void channelInactive(ChannelHandlerContext ctx) throws Exception {
    logger.info("客户端连接断开");
    super.channelInactive(ctx);
  }
}