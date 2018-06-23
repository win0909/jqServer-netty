/**
 * Copyright (C), 2015-2018
 * FileName: INetworkEventListener
 * Author:   zhao
 * Date:     2018/6/22 10:46
 * Description: 网络事件监听
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.lizhaoblog.base.network.listener;

import io.netty.channel.ChannelHandlerContext;

/**
 * 〈一句话功能简述〉<br>
 * 〈网络事件监听〉
 *
 * @author zhao
 * @date 2018/6/22 10:46
 * @since 1.0.0
 */
public interface INetworkEventListener {

  /**
   * 连接建立
   *
   * @param ctx ChannelHandlerContext
   */
  void onConnected(ChannelHandlerContext ctx);

  /**
   * 连接断开
   *
   * @param ctx ChannelHandlerContext
   */
  void onDisconnected(ChannelHandlerContext ctx);

  /**
   * 异常发生
   *
   * @param ctx       ChannelHandlerContext
   * @param throwable 异常
   */
  void onExceptionCaught(ChannelHandlerContext ctx, Throwable throwable);

}