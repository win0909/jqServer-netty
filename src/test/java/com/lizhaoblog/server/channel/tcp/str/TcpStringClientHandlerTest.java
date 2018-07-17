/*
 * Copyright (C), 2015-2018
 * FileName: TcpStringClientHandlerTest
 * Author:   zhao
 * Date:     2018/6/12 15:26
 * Description: SocketClientHandlerTest的
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.lizhaoblog.server.channel.tcp.str;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 〈一句话功能简述〉<br>
 * 〈TcpMessageStringHandler的测试类〉
 *
 * @author zhao
 * @date 2018/6/12 15:26
 * @see TcpMessageStringHandler
 * @since 1.0.0
 */
public class TcpStringClientHandlerTest extends SimpleChannelInboundHandler<Object> {
  private static final Logger logger = LoggerFactory.getLogger(TcpStringClientHandlerTest.class);

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable arg1) {
    logger.info("异常发生", arg1);
  }

  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    super.channelRead(ctx, msg);
  }

  @Override
  protected void channelRead0(ChannelHandlerContext ctx, Object data) throws Exception {
    logger.info("数据内容：data=" + data);

    //关闭链路
    //    ctx.close();
    //    channelInactive(ctx);
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