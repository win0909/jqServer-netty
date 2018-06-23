/*
 * Copyright (C), 2015-2018
 * FileName: TcpServerStringInitializer
 * Author:   zhao
 * Date:     2018/6/12 15:00
 * Description: TcpServerInitializer请求处理器
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.lizhaoblog.server.channel.tcp.str;

import com.lizhaoblog.server.pojo.ServerConfig;

import org.springframework.stereotype.Component;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * 〈一句话功能简述〉<br>
 * 〈TcpServerInitializer请求处理器〉
 *
 * @author zhao
 * @date 2018/6/12 15:00
 * @since 1.0.0
 */
@Component
public class TcpServerStringInitializer extends ChannelInitializer<SocketChannel> {

  @Override
  protected void initChannel(SocketChannel ch) {
    ChannelPipeline pipeline = ch.pipeline();
    pipeline.addLast("decoder", new StringDecoder());
    pipeline.addLast("encoder", new StringEncoder());
    TcpMessageStringHandler tcpMessageStringHandler = (TcpMessageStringHandler) ServerConfig.getInstance().getApplicationContext()
            .getBean("tcpMessageStringHandler");
    pipeline.addLast(tcpMessageStringHandler);


  }

}
