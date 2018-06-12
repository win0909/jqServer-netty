/**
 * Copyright (C), 2015-2018
 * FileName: TcpStringClientTest
 * Author:   zhao
 * Date:     2018/6/12 15:22
 * Description: TcpServerStringInitializer的测试类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.lizhaobolg.server.channel.tcp.str;

import com.lizhaobolg.common.CommonValue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * 〈一句话功能简述〉<br>
 * 〈TcpServerStringInitializer的测试类〉
 *
 * @author zhao
 * @date 2018/6/12 15:22
 * @see TcpServerStringInitializer
 * @since 1.0.0
 */
public class TcpStringClientTest {
  private static final Logger logger = LoggerFactory.getLogger(TcpStringClientTest.class);
  private static EventLoopGroup group = new NioEventLoopGroup();

  public void run() throws InterruptedException {
    Bootstrap bootstrap = new Bootstrap();
    bootstrap.group(group);
    bootstrap.channel(NioSocketChannel.class);
    bootstrap.handler(new ChannelInitializer() {
      @Override
      protected void initChannel(Channel ch){
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast("decoder", new StringDecoder());
        pipeline.addLast("encoder", new StringEncoder());
        pipeline.addLast(new TcpStringClientHandlerTest());
      }
    });

    // 连接服务端
    ChannelFuture channelFuture = bootstrap.connect(CommonValue.IP,CommonValue.PORT).sync();

    String msg = "小王，我是客户端";
    channelFuture.channel().writeAndFlush(msg);
    logger.info("向Socket服务器发送数据:" + msg);

    channelFuture.channel().closeFuture().sync();
  }
}