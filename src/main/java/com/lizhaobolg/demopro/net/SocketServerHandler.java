/**
 * Copyright (C), 2015-2018
 * FileName: SocketServerHandler
 * Author:   zhao
 * Date:     2018/6/10 21:27
 * Description: SocketServer的消息处理类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.lizhaobolg.demopro.net;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 〈一句话功能简述〉<br>
 * 〈SocketServer的消息处理类〉
 *
 * @author zhao
 * @date 2018/6/10
 * @since 1.0.0
 */
public class SocketServerHandler extends SimpleChannelInboundHandler<String> {
  private static final Logger logger = LoggerFactory.getLogger(SocketServer.class);

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable throwable) {
    logger.debug("异常发生", throwable);
  }

  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    super.channelRead(ctx, msg);
  }

  @Override
  protected void channelRead0(ChannelHandlerContext ctx, String msg) {
    logger.info("数据内容：data=" + msg);
    String result = "小李，我是服务器，我收到你的信息了。";

    //这行很重要，DelimiterBasedFrameDecoder以这个作为消息分割，
    // 如果没有换行符的话，客户端就没办法接受到
//    result += "\r\n";
    ctx.writeAndFlush(result);

  }

  @Override
  public void channelActive(ChannelHandlerContext ctx) throws Exception {
    logger.info("建立连接");
    super.channelActive(ctx);
  }

  @Override
  public void channelInactive(ChannelHandlerContext ctx) throws Exception {
    logger.info("连接断开");
    super.channelInactive(ctx);
  }
}