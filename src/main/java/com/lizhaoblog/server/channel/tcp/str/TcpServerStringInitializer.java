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

import com.lizhaoblog.base.constant.ConstantValue;
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

  @Override
  protected void initChannel(SocketChannel ch) {
    ChannelPipeline pipeline = ch.pipeline();
    pipeline.addLast("encoder", new MessageEncoder());
    pipeline.addLast("decoder", new MessageDecoder(ConstantValue.MESSAGE_CODEC_MAX_FRAME_LENGTH,
            ConstantValue.MESSAGE_CODEC_LENGTH_FIELD_LENGTH, ConstantValue.MESSAGE_CODEC_LENGTH_FIELD_OFFSET,
            ConstantValue.MESSAGE_CODEC_LENGTH_ADJUSTMENT, ConstantValue.MESSAGE_CODEC_INITIAL_BYTES_TO_STRIP, false,
            ServerConfig.getInstance().getMessageType()));

    TcpMessageStringHandler tcpMessageStringHandler = (TcpMessageStringHandler) ServerConfig.getInstance()
            .getApplicationContext().getBean("tcpMessageStringHandler");
    pipeline.addLast(tcpMessageStringHandler);

  }

}
