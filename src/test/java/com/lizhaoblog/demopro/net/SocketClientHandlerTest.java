/*
 * Copyright (C), 2015-2018
 * FileName: SocketClientHandlerTest
 * Author:   zhao
 * Date:     2018/6/10 21:36
 * Description: SocketClient的测试类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.lizhaoblog.demopro.net;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 〈一句话功能简述〉<br>
 * 〈SocketClient的测试类〉
 *
 * @author zhao
 * @date 2018/6/10
 * @see SocketServer
 * @since 1.0.0
 */
public class SocketClientHandlerTest extends SimpleChannelInboundHandler<String> {
  private static final Logger logger = LoggerFactory.getLogger(SocketClientTest.class);

  @Override
  public void exceptionCaught(ChannelHandlerContext arg0, Throwable arg1) {
    logger.info("异常发生", arg1);
  }

  @Override
  public void channelRead(ChannelHandlerContext arg0, Object msg) throws Exception {
    super.channelRead(arg0, msg);
  }

  @Override
  protected void channelRead0(ChannelHandlerContext arg0, String data) {
    logger.info("数据内容：data=" + data);
  }

  @Override
  public void channelActive(ChannelHandlerContext ctx) throws Exception {
    logger.info("客户端连接建立");
    super.channelActive(ctx);
  }

  @Override
  public void channelInactive(ChannelHandlerContext ctx) throws Exception {
    logger.info("客户端连接断开");
    super.channelInactive(ctx);
  }
}