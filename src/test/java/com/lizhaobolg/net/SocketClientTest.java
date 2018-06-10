/**
 * Copyright (C), 2015-2018
 * FileName: SocketServerTest
 * Author:   zhao
 * Date:     2018/6/10 21:35
 * Description: SocketServer的测试类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.lizhaobolg.net;

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
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * 〈一句话功能简述〉<br>
 * 〈SocketServer的测试类〉
 *
 * @author zhao
 * @create 2018/6/10
 * @since 1.0.0
 */
public class SocketClientTest {
  private static final Logger logger = LoggerFactory.getLogger(SocketClientTest.class);
  private static final String IP = "127.0.0.1";
  private static final int PORT = 8088;

  private static EventLoopGroup group = new NioEventLoopGroup();

  @SuppressWarnings("rawtypes")
  protected static void run() throws Exception {
    Bootstrap bootstrap = new Bootstrap();
    bootstrap.group(group);
    bootstrap.channel(NioSocketChannel.class);
    bootstrap.handler(new ChannelInitializer() {
      @Override
      protected void initChannel(Channel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        /*
         * 这个地方的 必须和服务端对应上。否则无法正常解码和编码
         *
         * 解码和编码 我将会在下一张为大家详细的讲解。再次暂时不做详细的描述
         *
         * */
        pipeline.addLast("framer", new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
        pipeline.addLast("decoder", new StringDecoder());
        pipeline.addLast("encoder", new StringEncoder());
        pipeline.addLast(new SocketClientHandlerTest());
      }
    });

    // 连接服务端
    ChannelFuture channelFuture = bootstrap.connect(IP, PORT).sync();

    String msg = "小王，我是客户端";
    //这行很重要，StringDecoder以这个作为消息分割，
    // 如果没有换行符的话，服务端就没办法接受到
    msg += "\r\n";
    channelFuture.channel().writeAndFlush(msg);
    logger.info("向Socket服务器发送数据:" + msg);

    channelFuture.channel().closeFuture().sync();

  }

  public static void main(String[] args) {
    logger.info("开始连接Socket服务器...");
    try {
      run();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      group.shutdownGracefully();
    }
  }
}