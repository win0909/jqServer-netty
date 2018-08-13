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

import java.util.List;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;

/**
 * 〈一句话功能简述〉<br>
 * 〈WebSocketFrame转换成IMessage〉
 *
 * @author zhao
 * @date 2018/8/13 19:25
 * @since 1.0.1
 */
public class WebSocketFrameToIMessageDecoder extends MessageToMessageDecoder<WebSocketFrame> {
  @Override
  protected void decode(ChannelHandlerContext channelHandlerContext, WebSocketFrame o, List list) throws Exception {

  }


}
