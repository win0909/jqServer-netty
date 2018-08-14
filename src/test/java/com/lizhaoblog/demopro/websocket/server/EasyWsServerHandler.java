/*
 * Copyright (C), 2015-2018
 * FileName: EasyWsServerHandler
 * Author:   zhao
 * Date:     2018/8/14 10:44
 * Description: 简单的WebSocket服务器
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.lizhaoblog.demopro.websocket.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpUtil;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshakerFactory;
import io.netty.util.CharsetUtil;

/**
 * 〈一句话功能简述〉<br>
 * 〈简单的WebSocket服务器〉
 *
 * @author zhao
 * @date 2018/8/14 10:44
 * @since 1.0.1
 */
public class EasyWsServerHandler extends SimpleChannelInboundHandler<Object> {
  @Override
  protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object msg) throws Exception {
    if (msg instanceof FullHttpRequest) {
      // 传统的HTTP接入
      handleHttpMessage(channelHandlerContext, msg);
    } else if (msg instanceof WebSocketFrame) {
      // WebSocket接入
      handleWebSocketMessage(channelHandlerContext, msg);
    }

  }

  /**
   * 处理WebSocket中的Http消息
   *
   * @param ctx 上下文
   * @param msg 消息
   */
  private void handleHttpMessage(ChannelHandlerContext ctx, Object msg) {
    // 传统的HTTP接入
    FullHttpRequest request = (FullHttpRequest) msg;

    // 如果HTTP解码失败，返回HHTP异常
    if (!request.decoderResult().isSuccess() || (!"websocket".equals(request.headers().get("Upgrade")))) {
      sendHttpResponse(ctx, request, new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST));
      return;
    }

    // 正常WebSocket的Http连接请求，构造握手响应返回
    WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory(
            "ws://" + request.headers().get(HttpHeaderNames.HOST), null, false);
    WebSocketServerHandshaker handshaker = wsFactory.newHandshaker(request);
    if (handshaker == null) { // 无法处理的websocket版本
      WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
    } else { // 向客户端发送websocket握手,完成握手
      handshaker.handshake(ctx.channel(), request);
    }
  }

  /**
   * Http返回
   *
   * @param ctx
   * @param request
   * @param response
   */
  public static void sendHttpResponse(ChannelHandlerContext ctx, FullHttpRequest request, FullHttpResponse response) {
    // 返回应答给客户端
    if (response.status().code() != 200) {
      ByteBuf buf = Unpooled.copiedBuffer(response.status().toString(), CharsetUtil.UTF_8);
      response.content().writeBytes(buf);
      buf.release();
      HttpUtil.setContentLength(response, response.content().readableBytes());
    }

    // 如果是非Keep-Alive，关闭连接
    ChannelFuture f = ctx.channel().writeAndFlush(response);
    if (!HttpUtil.isKeepAlive(request) || response.status().code() != 200) {
      f.addListener(ChannelFutureListener.CLOSE);
    }
  }

  /**
   * 处理WebSocket中的WebSocket消息
   *
   * @param ctx 上下文
   * @param msg 消息
   */
  private void handleWebSocketMessage(ChannelHandlerContext ctx, Object msg) {
    //    ByteBuf content = ((WebSocketFrame) msg).content();
    WebSocketFrame frame = (WebSocketFrame) msg;
    if (frame instanceof TextWebSocketFrame) {
      TextWebSocketFrame textFrame = (TextWebSocketFrame) frame;
      System.out.println("服务器：接收到你的TextWebSocketFrame消息，内容是 " + textFrame.text());

      // 返回消息给客户端
      ctx.writeAndFlush(new TextWebSocketFrame("我是服务器，我是服务器"));

    } else if (frame instanceof BinaryWebSocketFrame) {
      System.out.println("服务器：接收到你的BinaryWebSocketFrame消息，内容是 ");
      ByteBuf content = frame.content();
      byte[] result = new byte[content.readableBytes()];
      content.readBytes(result);
      for (byte b : result) {
        System.out.print(b);
        System.out.print(",");
      }
      System.out.println();
      ctx.writeAndFlush(new BinaryWebSocketFrame(Unpooled.copiedBuffer(result)));
    }

  }

  @Override
  public void channelActive(ChannelHandlerContext ctx) {
    System.out.println("服务器：连接建立");
  }

  @Override
  public void channelInactive(ChannelHandlerContext ctx) {
    System.out.println("服务器：断开连接");
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable throwable) {
    System.out.println("服务器：异常发送");
    throwable.printStackTrace();
  }

}
