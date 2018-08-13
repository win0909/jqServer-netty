/*
 * Copyright (C), 2015-2018
 * FileName: WebSocketChannelInitializer
 * Author:   zhao
 * Date:     2018/8/10 11:42
 * Description: webSocket的channel
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.lizhaoblog.server.channel.websocket;

import com.lizhaoblog.base.constant.ConstantValue;
import com.lizhaoblog.base.message.codec.IMessageToWebSocketFrameEncoder;
import com.lizhaoblog.base.message.codec.MessageDecoder;
import com.lizhaoblog.base.message.codec.MessageEncoder;
import com.lizhaoblog.base.message.codec.WebSocketFrameToIMessageDecoder;
import com.lizhaoblog.server.channel.tcp.str.TcpMessageStringHandler;
import com.lizhaoblog.server.pojo.ServerConfig;

import org.springframework.stereotype.Component;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * 〈一句话功能简述〉<br>
 * 〈webSocket的channel〉
 *
 * @author zhao
 * @date 2018/8/10 11:42
 * @since 1.0.1
 */
@Component
public class WebSocketChannelInitializer extends ChannelInitializer<SocketChannel> {

  @Override
  protected void initChannel(SocketChannel ch) {
    ChannelPipeline pipeline = ch.pipeline();
    pipeline.addLast("http-codec", new HttpServerCodec()); // Http消息编码解码
    pipeline.addLast("aggregator", new HttpObjectAggregator(65536)); // Http消息组装
    pipeline.addLast("http-chunked", new ChunkedWriteHandler()); // WebSocket通信支持

    //    pipeline.addLast("encoder", new MessageEncoder());
    //    pipeline.addLast("decoder", new MessageDecoder(ConstantValue.MESSAGE_CODEC_MAX_FRAME_LENGTH,
    //            ConstantValue.MESSAGE_CODEC_LENGTH_FIELD_LENGTH, ConstantValue.MESSAGE_CODEC_LENGTH_FIELD_OFFSET,
    //            ConstantValue.MESSAGE_CODEC_LENGTH_ADJUSTMENT, ConstantValue.MESSAGE_CODEC_INITIAL_BYTES_TO_STRIP, false,
    //            ServerConfig.getInstance().getMessageType()));

    pipeline.addLast("encoder", new IMessageToWebSocketFrameEncoder());
    pipeline.addLast("decoder", new WebSocketFrameToIMessageDecoder());

    WebSocketHandler webSocketHandler = (WebSocketHandler) ServerConfig.getInstance().getApplicationContext()
            .getBean("webSocketHandler");
    pipeline.addLast(webSocketHandler);

  }

}
