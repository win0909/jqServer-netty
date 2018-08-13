/*
 * Copyright (C), 2015-2018
 * FileName: WebSocketFrameToIMessageDecoder
 * Author:   zhao
 * Date:     2018/8/13 19:25
 * Description: WebSocketFrame转换成IMessage
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.lizhaoblog.base.message.codec;

import com.lizhaoblog.base.constant.ConstantValue;
import com.lizhaoblog.base.message.IMessage;
import com.lizhaoblog.server.pojo.ServerConfig;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;

/**
 * 〈一句话功能简述〉<br>
 * 〈WebSocketFrame转换成IMessage〉
 *
 * @author zhao
 * @date 2018/8/13 19:25
 * @since 1.0.1
 */
public class WebSocketFrameToIMessageDecoder extends MessageToMessageDecoder<Object> {

  @Override
  protected void decode(ChannelHandlerContext ctx, Object msg, List<Object> out) throws Exception {
    if (msg instanceof FullHttpRequest) {
      // 传统的HTTP接入
      out.add(msg);
    } else if (msg instanceof WebSocketFrame) {
      // out.add(msg)

      // WebSocket接入
      ByteBuf content = ((WebSocketFrame) msg).content();
      MessageDecoder messageDecoder = new MessageDecoder(ConstantValue.MESSAGE_CODEC_MAX_FRAME_LENGTH,
              ConstantValue.MESSAGE_CODEC_LENGTH_FIELD_LENGTH, ConstantValue.MESSAGE_CODEC_LENGTH_FIELD_OFFSET,
              ConstantValue.MESSAGE_CODEC_LENGTH_ADJUSTMENT, ConstantValue.MESSAGE_CODEC_INITIAL_BYTES_TO_STRIP, false,
              ServerConfig.getInstance().getMessageType());
      IMessage iMessage = messageDecoder.decodePub(ctx, content);
      out.add(iMessage);

    } else {
      out.add(msg);
    }

  }
}
