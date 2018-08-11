/*
 * Copyright (C), 2015-2018
 * FileName: HttpResponseUtil
 * Author:   zhao
 * Date:     2018/8/11 12:16
 * Description: 处理http的工具类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.lizhaoblog.base.util;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpUtil;
import io.netty.util.CharsetUtil;

/**
 * 〈一句话功能简述〉<br>
 * 〈处理http的工具类〉
 *
 * @author zhao
 * @date 2018/8/11 12:16
 * @since 1.0.1
 */
public final class HttpResponseUtil {

  /**
   * Http返回
   *
   * @param ctx
   * @param request
   * @param response
   */
  public static void sendHttpResponse(ChannelHandlerContext ctx, FullHttpRequest request, FullHttpResponse response) {
    // 返回应答给客户端
    if (response.status().code() != 200) {
      ByteBuf buf = Unpooled.copiedBuffer(response.status().toString(), CharsetUtil.UTF_8);
      response.content().writeBytes(buf);
      buf.release();
      HttpUtil.setContentLength(response, response.content().readableBytes());
    }

    // 如果是非Keep-Alive，关闭连接
    ChannelFuture f = ctx.channel().writeAndFlush(response);
    if (!HttpUtil.isKeepAlive(request) || response.status().code() != 200) {
      f.addListener(ChannelFutureListener.CLOSE);
    }
  }

  private HttpResponseUtil() {
  }

}