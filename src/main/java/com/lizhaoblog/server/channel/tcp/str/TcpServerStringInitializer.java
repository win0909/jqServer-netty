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

import com.lizhaoblog.base.message.codec.MessageDecoder;
import com.lizhaoblog.base.message.codec.MessageEncoder;
import com.lizhaoblog.server.pojo.ServerConfig;

import org.springframework.stereotype.Component;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

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
  private static final int MAX_FRAME_LENGTH = 1024 * 1024;
  private static final int LENGTH_FIELD_LENGTH = 4;
  private static final int LENGTH_FIELD_OFFSET = 2;
  private static final int LENGTH_ADJUSTMENT = 0;
  private static final int INITIAL_BYTES_TO_STRIP = 0;

  @Override
  protected void initChannel(SocketChannel ch) {
    ChannelPipeline pipeline = ch.pipeline();
    //    pipeline.addLast("decoder", new StringDecoder());
    //    pipeline.addLast("encoder", new StringEncoder());
    //    pipeline.addLast("encoder", new StringEncoder());
    pipeline.addLast("encoder", new MessageEncoder());
    pipeline.addLast("decoder",
            new MessageDecoder(MAX_FRAME_LENGTH, LENGTH_FIELD_LENGTH, LENGTH_FIELD_OFFSET, LENGTH_ADJUSTMENT,
                    INITIAL_BYTES_TO_STRIP, false));

    TcpMessageStringHandler tcpMessageStringHandler = (TcpMessageStringHandler) ServerConfig.getInstance()
            .getApplicationContext().getBean("tcpMessageStringHandler");
    pipeline.addLast(tcpMessageStringHandler);

  }

}
