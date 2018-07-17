/*
 * Copyright (C), 2015-2018
 * FileName: MessageEncoder
 * Author:   zhao
 * Date:     2018/7/16 14:58
 * Description: 消息编码器
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.lizhaoblog.base.message.codec;

import com.lizhaoblog.base.constant.ConstantValue;
import com.lizhaoblog.base.message.StringMessage;

import java.nio.charset.Charset;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * 〈一句话功能简述〉<br>
 * 〈消息编码器〉
 *
 * @author zhao
 * @date 2018/7/16 14:58
 * @since 1.0.1
 */
public class MessageEncoder extends MessageToByteEncoder<StringMessage> {

  @Override
  protected void encode(ChannelHandlerContext ctx, StringMessage msg, ByteBuf out) throws Exception {
    if (null == msg) {
      throw new Exception("msg is null");
    }

    String body = msg.getBody();
    byte[] bodyBytes = body.getBytes(Charset.forName(ConstantValue.PROJECT_CHARSET));
    out.writeInt(msg.getMessageId());
    out.writeInt(msg.getStatusCode());
    out.writeInt(bodyBytes.length);
    out.writeBytes(bodyBytes);
  }

}
