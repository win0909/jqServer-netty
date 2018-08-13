/*
 * Copyright (C), 2015-2018
 * FileName: WebSocketFrameEncoder
 * Author:   zhao
 * Date:     2018/8/13 17:12
 * Description: WebSocketFrameEncoder编码器
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.lizhaoblog.base.message.codec;

import com.lizhaoblog.base.exception.MessageCodecException;
import com.lizhaoblog.base.message.IMessage;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.EmptyByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;

/**
 * 〈一句话功能简述〉<br>
 * 〈WebSocketFrameEncoder编码器〉
 *
 * @author zhao
 * @date 2018/8/13 17:12
 * @since 1.0.1
 */
public class IMessageToWebSocketFrameEncoder extends MessageToMessageEncoder<IMessage> {
  @Override
  protected void encode(ChannelHandlerContext channelHandlerContext, IMessage iMessage, List<Object> list)
          throws Exception {
    //组合缓冲区
    CompositeByteBuf byteBuf = Unpooled.compositeBuffer();

    byte[] bodyBytes = iMessage.getBodyByte();
    byteBuf.writeShort(iMessage.getMessageId());
    byteBuf.writeShort(iMessage.getStatusCode());
    byteBuf.writeInt(bodyBytes.length);
    byteBuf.writeBytes(bodyBytes);

    list.add(new BinaryWebSocketFrame(byteBuf));
  }
}
