/**
 * Copyright (C), 2015-2018
 * FileName: INetworkConsumer
 * Author:   zhao
 * Date:     2018/6/23 21:06
 * Description: 网络消息处理器
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.lizhaoblog.base.network.customer;

import io.netty.channel.Channel;

/**
 * 〈一句话功能简述〉<br>
 * 〈网络消息处理器〉
 *
 * @author zhao
 * @date 2018/6/23 21:06
 * @since 1.0.0
 */
public interface INetworkConsumer {
  /**
   * 执行具体的指令
   *
   * @param message message 客户端发送的消息
   * @param channel channel 与客户端连接的管道
   */
  void consume(String message, Channel channel);
}